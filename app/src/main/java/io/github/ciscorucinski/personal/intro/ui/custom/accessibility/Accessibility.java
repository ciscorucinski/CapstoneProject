package io.github.ciscorucinski.personal.intro.ui.custom.accessibility;

import android.view.ViewGroup;

// TODO Make this a standalone Library

@SuppressWarnings("unused")
public class Accessibility {

    public static ViewGroupAccessibility with(ViewGroup viewGroup) {
        return new ViewGroupAccessibility(viewGroup);
    }

    // TODO add ViewAccessibility?? for a single View. That is because I cannot do the following...

    //     Accessibility.with(new View(...))

    //  However, I can do the following...

    //     Accessibility.with(new FrameLayout(...))

}