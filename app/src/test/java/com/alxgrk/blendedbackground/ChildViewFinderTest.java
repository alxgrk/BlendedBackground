package com.alxgrk.blendedbackground;

import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ChildViewFinderTest {

    private View mockView;

    private ViewGroup mockGroup;

    @Before
    public void setUp() throws Exception {
        mockView = Mockito.mock(View.class);

        mockGroup = Mockito.mock(ViewGroup.class);
        when(mockGroup.getChildCount()).thenReturn(1);
        when(mockGroup.getChildAt(0)).thenReturn(mockView);
    }

    @Test
    public void getAllChildren_singleView() throws Exception {
        ChildViewFinder uut = new ChildViewFinder(mockView);

        List<View> actual = uut.getAllChildren();

        assertEquals(1, actual.size());
        assertTrue(actual.contains(mockView));
    }

    @Test
    public void getAllChildren_viewGroup() throws Exception {
        ChildViewFinder uut = new ChildViewFinder(mockGroup);

        List<View> actual = uut.getAllChildren();

        assertEquals(2, actual.size());
        assertTrue(actual.contains(mockView));
        assertTrue(actual.contains(mockGroup));
    }

}