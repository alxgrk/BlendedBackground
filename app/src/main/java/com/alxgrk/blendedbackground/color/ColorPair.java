package com.alxgrk.blendedbackground.color;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

/**
 * This class encapsulates two colors.
 */
public class ColorPair {

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
        Integer unchanged = pair.second;
        pair = Pair.create(upper, unchanged);
    }

    public void setLower(@ColorInt int lower) {
        Integer unchanged = pair.first;
        pair = Pair.create(unchanged, lower);
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
