package com.alxgrk.blendedbackground.color;

import static org.junit.Assert.*;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GradientTest {

    private static final int COLOR_1 = Color.RED;

    private static final int COLOR_2 = Color.GRAY;

    private static final int VIEW_WIDTH = 400;

    private static final int VIEW_HEIGHT = 200;

    private static final int RADIUS_QUADRATIC = 100;

    private static final int RADIUS_RECT = 150;

    @Test
    public void testLinear() throws Exception {
        Gradient uut = new Gradient(VIEW_WIDTH, VIEW_HEIGHT, COLOR_1, COLOR_2, Gradient.GradientType.LINEAR);

        Shader shader = uut.getShader();

        assertTrue(shader instanceof LinearGradient);
    }

    @Test
    public void testRadial() throws Exception {
        Gradient uut = new Gradient(VIEW_WIDTH, VIEW_HEIGHT, COLOR_1, COLOR_2, Gradient.GradientType.RADIAL);

        Shader shader = uut.getShader();

        assertTrue(shader instanceof RadialGradient);
    }

    @Test
    public void testParentRadius_quadratic() throws Exception {
        Gradient uut = new Gradient(VIEW_HEIGHT, VIEW_HEIGHT, COLOR_1, COLOR_2, Gradient.GradientType.RADIAL);

        int actualRadius = uut.getParentRadius();

        assertEquals(RADIUS_QUADRATIC, actualRadius);
    }

    @Test
    public void testParentRadius_rect() throws Exception {
        Gradient uut = new Gradient(VIEW_WIDTH, VIEW_HEIGHT, COLOR_1, COLOR_2, Gradient.GradientType.RADIAL);

        int actualRadius = uut.getParentRadius();

        assertEquals(RADIUS_RECT, actualRadius);
    }
}