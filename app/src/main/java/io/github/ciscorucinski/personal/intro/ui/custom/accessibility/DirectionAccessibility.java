package io.github.ciscorucinski.personal.intro.ui.custom.accessibility;

import android.support.annotation.IdRes;
import android.view.View;
import android.view.ViewGroup;

import io.github.ciscorucinski.personal.intro.ui.custom.accessibility.interfaces.Direction;

public class DirectionAccessibility extends Direction {

    //    View parentView;
    private Accessibility.ViewGroupAccessibility accessibility;

    public DirectionAccessibility(Accessibility.ViewGroupAccessibility accessibility, ViewGroup viewGroup, @IdRes int parentViewId) {
        this.parentView = viewGroup.findViewById(parentViewId);
        this.accessibility = accessibility;
    }

    public DirectionAccessibility(Accessibility.ViewGroupAccessibility accessibility, View view) {
        this.parentView = view;
        this.accessibility = accessibility;
    }

    @Override
    public Direction up(@IdRes int viewId) {
        parentView.setNextFocusUpId(viewId);
        return this;
    }

    @Override
    public Direction down(@IdRes int viewId) {
        parentView.setNextFocusDownId(viewId);
        return this;
    }

    @Override
    public Direction left(@IdRes int viewId) {
        parentView.setNextFocusLeftId(viewId);
        return this;
    }

    @Override
    public Direction right(@IdRes int viewId) {
        parentView.setNextFocusRightId(viewId);
        return this;
    }

    @Override
    public Direction focusForward(@IdRes int viewId) {
        parentView.setNextFocusForwardId(viewId);
        return this;
    }

    @Override
    public Accessibility.ViewGroupAccessibility complete() {
        this.parentView.setFocusable(true);
        return this.accessibility;
    }
}