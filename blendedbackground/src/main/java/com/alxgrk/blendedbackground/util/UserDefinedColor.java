package com.alxgrk.blendedbackground.util;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.StyleableRes;

public class UserDefinedColor {

    @ColorInt
    @Nullable
    private Integer color = null;

    private final boolean isDefined;

    public UserDefinedColor(@ColorInt int color) {
        isDefined = true;
        this.color = color;
    }

    public UserDefinedColor(TypedArray typedArray, @StyleableRes int styleableRes) {
        isDefined = typedArray.hasValue(styleableRes);
        if (isDefined) {
            color = typedArray.getColor(styleableRes, Color.TRANSPARENT);
        }
    }

    public boolean isDefined() {
        return isDefined;
    }

    @Nullable
    public @ColorInt Integer getColor() {
        return color;
    }
}
