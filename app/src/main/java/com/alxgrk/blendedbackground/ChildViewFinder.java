package com.alxgrk.blendedbackground;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

class ChildViewFinder {

    private final List<View> allChildren;

    ChildViewFinder(View view) {
        allChildren = getAllChildren(view);
    }

    private List<View> getAllChildren(View view) {
        List<View> result = new ArrayList<>();
        result.add(view);

        if (!(view instanceof ViewGroup)) {
            return result;
        }

        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);

            result.addAll(getAllChildren(child));
        }
        return result;
    }

    List<View> getAllChildren() {
        return allChildren;
    }
}
