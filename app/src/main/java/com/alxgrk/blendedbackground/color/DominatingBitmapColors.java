package com.alxgrk.blendedbackground.color;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.graphics.Palette;

import java.util.List;

public class DominatingBitmapColors {

    private final ColorPair pair;

    public DominatingBitmapColors(Bitmap source) {
        pair = createPairFrom(source);
    }

    private ColorPair createPairFrom(Bitmap source) {
        Palette palette = Palette.from(source).maximumColorCount(2).generate();

        int first = Color.TRANSPARENT;
        int second = Color.TRANSPARENT;
        for(Palette.Swatch swatch : palette.getSwatches()) {
            if(Color.TRANSPARENT == first) {
                first = swatch.getRgb();
            } else if (Color.TRANSPARENT == second) {
                second = swatch.getRgb();
            }
        }

        return new ColorPair(first, second);
    }


    public ColorPair getColors() {
        return pair;
    }
}
