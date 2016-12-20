package com.alxgrk.blendedbackground.color;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Path;
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

public class Gradient {

    private View parent;
    private final int upper;
    private final int lower;
    private final Class<? extends Shader> type;

    public Gradient(View parent, @ColorInt int upper, @ColorInt int lower) {
        this(parent, upper, lower, LinearGradient.class);
        // TODO support other blending types
    }

    /**
     * Does not support other than {@link LinearGradient} yet.
     * @param parent
     * @param upper
     * @param lower
     * @param type
     */
    private Gradient(View parent, @ColorInt int upper, @ColorInt int lower, Class<? extends Shader> type) {
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
                return new LinearGradient(0, 0,
                        0, parent.getHeight(),
                        upper,
                        lower,
                        Shader.TileMode.CLAMP);
            }
        };
    }
}
