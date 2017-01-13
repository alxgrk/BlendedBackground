package com.alxgrk.blendedbackground.color;

import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.VisibleForTesting;

/**
 * The gradient for the specified view. <br>
 * Default gradient mode is LinearGradient.
 */
public class Gradient {

    private final ShapeDrawable.ShaderFactory shaderFactory;

    public enum GradientType {
        LINEAR(0),
        RADIAL(1);

        private final int i;

        GradientType(int i) {
            this.i = i;
        }

        public static GradientType from(int i) {
            for (GradientType type : values()) {
                if(type.i == i)
                    return type;
            }
            return LINEAR;
        }
    }

    private final int parentWidth;
    private final int parentHeight;
    private final int upper;
    private final int lower;
    private final GradientType type;

    /**
     * Constructor for a Gradient. <br>
     * Note that parent sizes MUST be larger than 0. If passing a value less than 0, it will get replaced by 1.
     * @param parentWidth
     * @param parentHeight
     * @param upper
     * @param lower
     * @param type
     */
    public Gradient(int parentWidth, int parentHeight, @ColorInt int upper, @ColorInt int lower, GradientType type) {
        this.parentWidth = parentWidth <= 0 ? 1 : parentWidth;
        this.parentHeight = parentHeight <= 0 ? 1 : parentHeight;
        this.upper = upper;
        this.lower = lower;
        this.type = type;

        shaderFactory = createShaderFactory();
    }

    Shader getShader() {
        return shaderFactory.resize(parentWidth, parentHeight);
    }

    public Drawable get() {
        return createGradient();
    }

    private Drawable createGradient() {
        return createDrawableFrom(shaderFactory);
    }

    private Drawable createDrawableFrom(ShapeDrawable.ShaderFactory sf) {
        PaintDrawable p = new PaintDrawable();
        p.setShape(new RectShape());
        p.setShaderFactory(sf);

        return p;
    }

    private ShapeDrawable.ShaderFactory createShaderFactory() {
        return new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(int width, int height) {
                switch (type) {
                    case RADIAL:
                        return new RadialGradient(parentWidth / 2,
                                parentHeight / 2, getParentRadius(),
                                upper, lower,
                                Shader.TileMode.CLAMP);

                    case LINEAR:
                    default:
                        return new LinearGradient(0, 0,
                                0, parentHeight,
                                upper, lower,
                                Shader.TileMode.CLAMP);
                }
            }
        };
    }

    @VisibleForTesting
    protected int getParentRadius() {
        int smallestSide = parentWidth < parentHeight ? parentWidth : parentHeight;
        int midOfWidthHeight = Math.abs(parentWidth - parentHeight) / 2;
        return Math.abs((smallestSide + midOfWidthHeight) / 2);
    }
}
