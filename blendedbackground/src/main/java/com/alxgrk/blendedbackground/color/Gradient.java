package com.alxgrk.blendedbackground.color;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.view.View;

/**
 * The gradient for the specified view. <br>
 * Default gradient mode is LinearGradient.
 */
public class Gradient {

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

    private View parent;
    private final int upper;
    private final int lower;
    private final GradientType type;

    public Gradient(View parent, @ColorInt int upper, @ColorInt int lower, GradientType type) {
        this.parent = parent;
        this.upper = upper;
        this.lower = lower;
        this.type = type;
    }

    public Drawable get() {
        return createGradient();
    }

    private Drawable createGradient() {
        ShapeDrawable.ShaderFactory sf = createShaderFactory();

        return createDrawableFrom(sf);
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
                        return new RadialGradient(parent.getWidth() / 2,
                                parent.getHeight() / 2, getParentRadius(),
                                upper, lower,
                                Shader.TileMode.CLAMP);

                    case LINEAR:
                    default:
                        return new LinearGradient(0, 0,
                                0, parent.getHeight(),
                                upper, lower,
                                Shader.TileMode.CLAMP);
                }
            }
        };
    }

    private float getParentRadius() {
        int smallestSide = parent.getWidth() < parent.getHeight() ? parent.getWidth() : parent.getHeight();
        int midOfWidthHeight = Math.abs(parent.getWidth() - parent.getHeight()) / 2;
        return (smallestSide + midOfWidthHeight) / 2;
    }
}
