package com.alxgrk.blendedbackground;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alxgrk.blendedbackground.color.ColorPair;
import com.alxgrk.blendedbackground.color.DominatingBitmapColors;
import com.alxgrk.blendedbackground.color.Gradient;
import com.alxgrk.blendedbackground.util.UserDefinedColor;

/**
 * TODO: document your custom view class.
 */
public class BlendedBackgroundLayout extends RelativeLayout {

    public static final class NoReferenceFoundException extends RuntimeException {
        NoReferenceFoundException() {
            super("No reference found. Add view:tag=\"@string/ref_tag\" to your layout xml " +
                    "or set both app:upper and app:lower.");
        }
    }

    private static final String TAG = "BlendedBackgroundLayout";

    /**
     * The two colors the view will blend.
     */
    private ColorPair colors =  new ColorPair(Color.TRANSPARENT, Color.TRANSPARENT);

    private String ref_tag;

    private View referencedView;

    // TODO let user edit fields dynamically

    private UserDefinedColor upper;

    private UserDefinedColor lower;

    private boolean invert;

    public BlendedBackgroundLayout(Context context) {
        super(context);
        init(null, 0);
    }

    public BlendedBackgroundLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BlendedBackgroundLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        ref_tag = getResources().getString(R.string.bb_ref_tag);

        if (null != attrs) {
            final TypedArray a = getContext().obtainStyledAttributes(
                    attrs, R.styleable.BlendedBackgroundLayout, defStyle, 0);

            upper = new UserDefinedColor(a, R.styleable.BlendedBackgroundLayout_upper_color);
            lower = new UserDefinedColor(a, R.styleable.BlendedBackgroundLayout_lower_color);
            invert = a.getBoolean(R.styleable.BlendedBackgroundLayout_invert, false);

            a.recycle();
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        View taggedView = findViewWithTag(ref_tag);
        if(null == referencedView || taggedView == referencedView) {
            referencedView = taggedView;
            update();
        }
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);

        Log.d(TAG, "added " + child);
        if(ref_tag.equals(child.getTag())) {
            referencedView = child;
            update();
        }
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);

        Log.d(TAG, "removed " + child);
        if(ref_tag.equals(child.getTag())) {
            referencedView = findViewWithTag(ref_tag);
            update();
        }
    }

    private void update() {
        if(null == referencedView) {
            if(!upper.isDefined() || !lower.isDefined())
                throw new NoReferenceFoundException();
        } else {
            colors = calculateBackgroundFor(referencedView, colors);
        }

        applyUserDefinitions(colors);

        Gradient gradient = new Gradient(this, colors.getUpper(), colors.getLower());
        this.setBackground(gradient.get());
        this.invalidate();
    }

    private ColorPair calculateBackgroundFor(@NonNull View child, @NonNull ColorPair colors) {
        Log.d(TAG, "Found " + getIdName(child) + " as corresponding view.");

        Drawable source;
        if (child instanceof ImageView) {
            source = ((ImageView) child).getDrawable();
        } else {
            source = child.getBackground();
        }

        if (null != source) {
            DominatingBitmapColors dominatingBitmapColors = new DominatingBitmapColors(((BitmapDrawable) source).getBitmap());
            colors = dominatingBitmapColors.getColors();
            Log.d(TAG, "Dominant colors: " + colors);
        } else {
            Log.e(TAG, "Could not retrieve image from specified view. Fallback to transparent background.");
        }

        return colors;
    }

    private String getIdName(@NonNull View view) {
        String idName;
        try {
            idName = getResources().getResourceName(view.getId());
        } catch (Resources.NotFoundException e) {
            Log.w(TAG, "Child " + view + " does not have a user defined id.");
            idName = view.toString();
        }
        return idName;
    }

    private void applyUserDefinitions(@NonNull ColorPair colors) {
        if(null != upper.getColor()) {
            colors.setUpper(upper.getColor());
        }
        if(null != lower.getColor()) {
            colors.setLower(lower.getColor());
        }
    }
}
