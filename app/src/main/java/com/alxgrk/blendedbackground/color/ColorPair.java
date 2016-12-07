package com.alxgrk.blendedbackground.color;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

/**
 * This class encapsulates two colors.
 */
public class ColorPair {

    @NonNull
    private final Pair<Integer, Integer> pair;

    ColorPair(@ColorInt int first, @ColorInt int second) {
        pair = Pair.create(first, second);
    }

    public @ColorInt int getFirst() {
        return pair.first;
    }

    public @ColorInt int getSecond() {
        return pair.second;
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
