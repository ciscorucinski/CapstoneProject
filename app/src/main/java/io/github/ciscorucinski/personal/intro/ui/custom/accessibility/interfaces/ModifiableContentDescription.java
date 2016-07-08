package io.github.ciscorucinski.personal.intro.ui.custom.accessibility.interfaces;

import java.util.List;

import io.github.ciscorucinski.personal.intro.ui.custom.accessibility.ViewGroupAccessibility;

@SuppressWarnings("unused")
public abstract class ModifiableContentDescription {

    protected StringBuilder prependText;
    protected StringBuilder appendText;

    protected boolean isModified;

    public abstract ModifiableContentDescription prepend(String prependedText);
    public abstract ModifiableContentDescription appendablePrepend(String prependedText);
    public abstract ModifiableContentDescription append(String appendedText);

    public abstract ModifiableContentDescription prepend(List<String> prependedTextList);
    public abstract ModifiableContentDescription append(List<String> appendedTextList);

    public abstract ViewGroupAccessibility complete();


}