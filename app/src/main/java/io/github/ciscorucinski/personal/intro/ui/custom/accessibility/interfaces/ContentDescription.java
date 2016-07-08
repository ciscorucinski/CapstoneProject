package io.github.ciscorucinski.personal.intro.ui.custom.accessibility.interfaces;

import io.github.ciscorucinski.personal.intro.ui.custom.accessibility.ViewGroupAccessibility;

@SuppressWarnings("unused")
public abstract class ContentDescription extends ModifiableContentDescription {

    protected StringBuilder mainText;

    public abstract ViewGroupAccessibility setContentDescription(String text);
    public abstract ViewGroupAccessibility setContentDescription(CharSequence text);

    public abstract ModifiableContentDescription setModifiableContentDescription(String text);
    public abstract ModifiableContentDescription setModifiableContentDescription(CharSequence text);

}