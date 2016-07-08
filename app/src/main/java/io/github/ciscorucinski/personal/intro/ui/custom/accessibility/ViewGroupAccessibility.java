package io.github.ciscorucinski.personal.intro.ui.custom.accessibility;

import android.support.annotation.IdRes;
import android.view.View;
import android.view.ViewGroup;

import io.github.ciscorucinski.personal.intro.ui.custom.accessibility.interfaces.ContentDescription;

@SuppressWarnings("unused")
public class ViewGroupAccessibility {

    private ViewGroup viewGroup;

    ViewGroupAccessibility(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    public DirectionAccessibility setFocusableNavigationOn(@IdRes int parentViewId) {
        return new DirectionAccessibility(this, viewGroup, parentViewId);
    }

    public DirectionAccessibility setFocusableNavigationOn(View parentView) {
        return new DirectionAccessibility(this, parentView);
    }

    public ViewGroupAccessibility requestFocusOn(int viewId) {
        viewGroup.findViewById(viewId).requestFocus();
        return this;
    }

    public ViewGroupAccessibility disableFocusableNavigationOn(@IdRes int... ids) {

        for (int id : ids) {
            viewGroup.findViewById(id)
                    .setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
        }
        return this;

    }

    public ContentDescription setAccessibilityTextOn(View parentView) {
        return new TextAccessibility(this, parentView);
    }

}