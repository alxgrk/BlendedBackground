package com.alxgrk.blendedbackground.color;

import android.graphics.Color;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColorPairTest {

    private static final int COLOR_1 = Color.RED;

    private static final int COLOR_2 = Color.GRAY;

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
}