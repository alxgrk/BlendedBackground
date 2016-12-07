package com.alxgrk.blendedbackground;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alxgrk.blendedbackground.color.ColorPair;
import com.alxgrk.blendedbackground.color.DominatingBitmapColors;
import com.alxgrk.blendedbackground.color.Gradient;

/**
 * TODO: document your custom view class.
 */
public class BlendedBackgroundLayout extends RelativeLayout {

    public static final class NoReferenceFoundException extends RuntimeException {
        public NoReferenceFoundException(String enteredValue) {
            super("No reference found for '" + enteredValue + "'");
        }
    }

    private static final String TAG = "BlendedBackgroundLayout";

    private String referencedViewName;

    private View correspondingView;

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
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.BlendedBackgroundLayout, defStyle, 0);

        referencedViewName = a.getString(R.styleable.BlendedBackgroundLayout_referring_element);
        if (null == referencedViewName) {
            throw new NoReferenceFoundException("(nothing entered)");
        }

        a.recycle();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        boolean foundReference = false;
        ChildViewFinder childViewFinder = new ChildViewFinder(this);

        for (View child : childViewFinder.getAllChildren()) {
            String childName = getIdName(child);

            if (childName.contains(referencedViewName)) {
                foundReference = true;
                calculateBackgroundFor(child);
            }
        }

        if(!foundReference) {
            throw new NoReferenceFoundException(referencedViewName);
        }
    }

    private String getIdName(View view) {
        String idName = "";
        try {
            idName = getResources().getResourceName(view.getId());
        } catch (Resources.NotFoundException e) {
            Log.d(TAG, "Child " + view + " does not have a user defined id; " +
                    "therefore not took into consideration.");
        }
        return idName;
    }

    private void calculateBackgroundFor(View child) {
        correspondingView = child;
        Log.d(TAG, "Found " + child + " as corresponding view.");

        Drawable source;
        if (child instanceof ImageView) {
            source = ((ImageView) child).getDrawable();
        } else {
            source = child.getBackground();
        }
        DominatingBitmapColors dominatingBitmapColors = new DominatingBitmapColors(((BitmapDrawable) source).getBitmap());
        ColorPair colors = dominatingBitmapColors.getColors();
        Log.d(TAG, "Dominant colors: " + colors);

        // TODO consider user attributes (fixed bottom/top color)

        Gradient gradient = new Gradient(this, colors.getFirst(), colors.getSecond());
        this.setBackground(gradient.get());
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);

        // TODO recognize when a new view was added, that has the specific attribute to be corresponding view
    }
}
