package com.alxgrk.blendedbackground.color;

import android.app.Instrumentation;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.ViewAsserts;
import android.util.AttributeSet;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class GradientTest {

    private static final int VIEW_HEIGHT = 200;

    private static final int COLOR_1 = Color.RED;

    private static final int COLOR_2 = Color.GRAY;

    private View mockView;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        mockView = new View(context);
    }

    @Test
    public void testCreation() throws Exception {
        Gradient uut = new Gradient(mockView, COLOR_1, COLOR_2);

        Drawable actual = uut.get();

        // FIXME complete test
    }
}