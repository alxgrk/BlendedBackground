package com.alxgrk.blendedbackground;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.alxgrk.blendedbackground.color.Gradient;
import com.alxgrk.blendedbackground.util.UserDefinedColor;

import java.util.Observable;
import java.util.Observer;

import lombok.Delegate;

/**
 * TODO: document your custom view class.
 */
public class BlendedBackgroundLayout extends RelativeLayout implements Observer {

    private static final String TAG = "BlendedBackgroundLayout";

    @Delegate(excludes = Observable.class)
    private BlendedBackground blendedBackground;

    private String refTag;

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
        if (null != attrs) {
            final TypedArray a = getContext().obtainStyledAttributes(
                    attrs, R.styleable.BlendedBackgroundLayout, defStyle, 0);

            UserDefinedColor upper = new UserDefinedColor(a, R.styleable.BlendedBackgroundLayout_upper_color);
            UserDefinedColor lower = new UserDefinedColor(a, R.styleable.BlendedBackgroundLayout_lower_color);
            boolean upperBlendIn = a.getBoolean(R.styleable.BlendedBackgroundLayout_upper_blend_in, false);
            boolean lowerBlendIn = a.getBoolean(R.styleable.BlendedBackgroundLayout_lower_blend_in, false);
            boolean invert = a.getBoolean(R.styleable.BlendedBackgroundLayout_invert, false);

            int definedType = a.getInt(R.styleable.BlendedBackgroundLayout_gradient_type, 0);
            Gradient.GradientType gradientType = Gradient.GradientType.from(definedType);

            blendedBackground = BlendedBackground.builder(getContext(), this)
                    .upper(upper).lower(lower)
                    .upperBlendIn(upperBlendIn).lowerBlendIn(lowerBlendIn)
                    .invert(invert).gradientType(gradientType)
                    .build();

            a.recycle();
        } else {
            blendedBackground = BlendedBackground.builder(getContext(), this).build();
        }

        blendedBackground.addObserver(this);
        refTag = blendedBackground.getRefTag();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        blendedBackground.setParentHeight(b - t);
        blendedBackground.setParentWidth(r - l);

        View taggedView = findViewWithTag(refTag);
        if(null == blendedBackground.getReferencedView() || taggedView == blendedBackground.getReferencedView()) {
            updateReference(taggedView);
        }
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);

        Log.d(TAG, "added " + child);
        updateReference(child);
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);

        Log.d(TAG, "removed " + child);

        if (child == blendedBackground.getReferencedView()) {
            View taggedView = findViewWithTag(refTag);
            if(taggedView != null) {
                updateReference(taggedView);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (blendedBackground == o) {
            refresh();
        }
    }

    private void updateReference(View child) {
        if(refTag.equals(child.getTag())) {
            blendedBackground.updateReferencedView(child);
            refresh();
        }
    }
    
    private void refresh() {
        Drawable result = blendedBackground.get();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(result);
        } else {
            this.setBackgroundDrawable(result);
        }

        this.invalidate();
    }
}
