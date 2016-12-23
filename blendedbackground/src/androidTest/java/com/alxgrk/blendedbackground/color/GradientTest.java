package com.alxgrk.blendedbackground.color;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.alxgrk.blendedbackground.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class GradientTest {

    private static final int COLOR_1 = Color.RED;

    private static final int COLOR_2 = Color.GRAY;

    private int viewSize;

    private Gradient uut;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        View testView = LayoutInflater.from(context).inflate(R.layout.test_view_parent, null);
        viewSize = testView.getWidth();

        uut = new Gradient(testView, COLOR_1, COLOR_2, Gradient.GradientType.LINEAR);
    }

    @Test
    public void testCreation() throws Exception {
        throw new RuntimeException("not working yet, viewSize always 0");
        /*
        PaintDrawable actual = (PaintDrawable) uut.get();

        assertEquals(viewSize, actual.getMinimumWidth());
        assertEquals(viewSize, actual.getMinimumHeight());
        assertTrue(actual.getShape() instanceof RectShape); */
    }
}