package com.alxgrk.blendedbackground.util;

import static org.mockito.Mockito.*;

import android.content.res.TypedArray;
import android.graphics.Color;

import com.alxgrk.blendedbackground.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class UserDefinedColorTest {

    private static final int USER_RES = R.styleable.BlendedBackgroundLayout_invert;

    private static final int USER_COLOR = Color.RED;

    private TypedArray mockArray;

    @Before
    public void setUp() throws Exception {
        mockArray = mock(TypedArray.class);
    }

    @Test
    public void isDefined_true() throws Exception {
        when(mockArray.hasValue(anyInt())).thenReturn(true);
        when(mockArray.getColor(anyInt(), anyInt())).thenReturn(USER_COLOR);

        UserDefinedColor uut = new UserDefinedColor(mockArray, USER_RES);
        boolean actualDefined = uut.isDefined();
        int actualColor = uut.getColor();

        assertEquals(true, actualDefined);
        assertEquals(USER_COLOR, actualColor);
    }

    @Test
    public void isDefined_false() throws Exception {
        when(mockArray.hasValue(anyInt())).thenReturn(false);

        UserDefinedColor uut = new UserDefinedColor(mockArray, USER_RES);
        boolean actualDefined = uut.isDefined();
        Integer actualColor = uut.getColor();

        assertEquals(false, actualDefined);
        assertNull(actualColor);
    }
}