package com.alxgrk.blendedbackground.color;

import android.graphics.Color;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ColorPairTest {

    private static final int COLOR_1 = Color.WHITE;

    private static final int COLOR_2 = Color.BLACK;

    private static final int COLOR_AVG = 0xFF7F7F7F;

    @Test
    public void testGetFirst() throws Exception {
        int expected = COLOR_1;
        ColorPair uut = new ColorPair(COLOR_1, COLOR_2);

        int actual = uut.getUpper();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetSecond() throws Exception {
        int expected = COLOR_2;
        ColorPair uut = new ColorPair(COLOR_1, COLOR_2);

        int actual = uut.getLower();

        assertEquals(expected, actual);
    }

    @Test
    public void testBlendUpper() throws Exception {
        ColorPair uut = new ColorPair(COLOR_1, COLOR_2);

        uut.blendUpper(COLOR_2);

        assertEquals("getUpper is " + Integer.toHexString(uut.getUpper()), COLOR_AVG, uut.getUpper());
    }

    @Test
    public void testBlendLower() throws Exception {
        ColorPair uut = new ColorPair(COLOR_1, COLOR_2);

        uut.blendLower(COLOR_1);

        assertEquals("getLower is " + Integer.toHexString(uut.getLower()), COLOR_AVG, uut.getLower());
    }

    @Test
    public void testInvert() throws Exception {
        ColorPair expected = new ColorPair(COLOR_2, COLOR_1);

        ColorPair actual = new ColorPair(COLOR_1, COLOR_2);
        actual.invert();

        assertEquals(expected, actual);
    }
}