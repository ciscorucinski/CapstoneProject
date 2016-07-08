package io.github.ciscorucinski.personal.intro.ui.custom.accessibility.interfaces;

import android.support.annotation.IdRes;
import android.view.View;

import io.github.ciscorucinski.personal.intro.ui.custom.accessibility.ViewGroupAccessibility;

@SuppressWarnings("unused")
public abstract class Direction {

    protected View parentView;

    public abstract Direction up(@IdRes int viewId);
    public abstract Direction down(@IdRes int viewId);
    public abstract Direction left(@IdRes int viewId);
    public abstract Direction right(@IdRes int viewId);

    public abstract Direction focusForward(@IdRes int viewId);

    public abstract ViewGroupAccessibility complete();

}