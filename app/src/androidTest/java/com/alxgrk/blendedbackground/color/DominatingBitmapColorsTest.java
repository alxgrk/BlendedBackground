package com.alxgrk.blendedbackground.color;

import android.graphics.Bitmap;
import android.graphics.Color;

import org.junit.Test;

import static org.junit.Assert.*;

public class DominatingBitmapColorsTest {

    private Bitmap unusableForPalette() {
        int width = 2;
        int height = 2;
        int[] colors = new int[] {Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK};

        return Bitmap.createBitmap(colors, width, height, Bitmap.Config.RGB_565);
    }

    private Bitmap usableForPalette() {
        int width = 10;
        int height = 10;
        int total = width * height;
        int[] colors = new int[total];

        for (int i = 0; i < total; i++) {
            if (i < total / 4) {
                colors[i] = Color.WHITE;
            } else if(i < total / 2) {
                colors[i] = Color.GRAY;
            } else {
                colors[i] = Color.BLACK;
            }
        }

        return Bitmap.createBitmap(colors, width, height, Bitmap.Config.RGB_565);
    }

    @Test
    public void testGetColor_unusable() throws Exception {
        DominatingBitmapColors uut = new DominatingBitmapColors(unusableForPalette());

        ColorPair actual = uut.getColors();

        assertEquals(Color.TRANSPARENT, actual.getFirst());
        assertEquals(Color.TRANSPARENT, actual.getSecond());
    }

    @Test
    public void testGetColor_usable() throws Exception {
        DominatingBitmapColors uut = new DominatingBitmapColors(usableForPalette());

        ColorPair actual = uut.getColors();
        int first = actual.getFirst();
        int second = actual.getSecond();
        
        assertNotEquals(Integer.toHexString(first), Color.WHITE, first);
        assertNotEquals(Integer.toHexString(first), Color.GRAY, first);
        assertNotEquals(Integer.toHexString(first), Color.BLACK, first);
        assertNotEquals(Integer.toHexString(first), Color.TRANSPARENT, first);

        assertNotEquals(Integer.toHexString(second), Color.WHITE, second);
        assertNotEquals(Integer.toHexString(second), Color.GRAY, second);
        assertNotEquals(Integer.toHexString(second), Color.BLACK, second);
        assertNotEquals(Integer.toHexString(second), Color.TRANSPARENT, second);
    }
}