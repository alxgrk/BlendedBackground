package com.alxgrk.blendedbackground.color;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.util.Pair;

/**
 * This class encapsulates two colors.
 */
public class ColorPair {

    private static final float RATIO = 0.5f;

    @NonNull
    private Pair<Integer, Integer> pair;

    public ColorPair(@ColorInt int upper, @ColorInt int lower) {
        pair = Pair.create(upper, lower);
    }

    public @ColorInt int getUpper() {
        return pair.first;
    }

    public @ColorInt int getLower() {
        return pair.second;
    }

    public void setUpper(@ColorInt int upper) {
        int unchanged = getLower();
        pair = Pair.create(upper, unchanged);
    }

    public void setLower(@ColorInt int lower) {
        int unchanged = getUpper();
        pair = Pair.create(unchanged, lower);
    }

    public void blendUpper(@ColorInt int color) {
        int avg = ColorUtils.blendARGB(color, getUpper(), RATIO);
        pair = Pair.create(avg, getLower());
    }

    public void blendLower(@ColorInt int color) {
        int avg = ColorUtils.blendARGB(color, getLower(), RATIO);
        pair = Pair.create(getUpper(), avg);
    }

    public void invert() {
        int upper = getUpper();
        int lower = getLower();
        pair = Pair.create(lower, upper);
    }

    /**
     * NOT GENERATED
     * @return the pair representing its members by a hex string
     */
    @Override
    public String toString() {
        return "ColorPair{" +
                "pair=" + Integer.toHexString(pair.first) +
                "," + Integer.toHexString(pair.second) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColorPair colorPair = (ColorPair) o;

        return pair.equals(colorPair.pair);

    }

    @Override
    public int hashCode() {
        return pair.hashCode();
    }
}
