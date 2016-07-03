package io.github.ciscorucinski.personal.intro.ui.custom.accessibility;

import android.view.View;

import java.util.List;

import io.github.ciscorucinski.personal.intro.ui.custom.accessibility.interfaces.ContentDescription;
import io.github.ciscorucinski.personal.intro.ui.custom.accessibility.interfaces.ModifiableContentDescription;

public class TextAccessibility extends ContentDescription {

    private final Accessibility.ViewGroupAccessibility accessibility;
    private final View parentView;

//    private boolean isModified = false;

    TextAccessibility(Accessibility.ViewGroupAccessibility accessibility, View parentView) {
        this.accessibility = accessibility;
        this.parentView = parentView;

        this.appendText = new StringBuilder();
        this.prependText = new StringBuilder();
    }

    @Override
    public ModifiableContentDescription prepend(String prependedText) {
        this.prependText.insert(0, prependedText);
        return this;
    }

    @Override
    public ModifiableContentDescription appendablePrepend(String prependedText) {
        this.prependText.append(prependedText);
        return this;
    }

    @Override
    public ModifiableContentDescription append(String appendedText) {
        this.appendText.append(appendedText);
        return this;
    }

    @Override
    public ModifiableContentDescription prepend(List<String> prependedTextList) {
        for (String text : prependedTextList) {
            this.prependText.append(text);
        }
        return this;
    }

    @Override
    public ModifiableContentDescription append(List<String> appendedTextList) {
        for (String text : appendedTextList) {
            this.appendText.append(text);
        }
        return this;
    }

    @Override
    public Accessibility.ViewGroupAccessibility setContentDescription(String text) {

        // if text is null, then main text will be a string with the value of "null". This will make the screen-reader ignore the text
        this.mainText = new StringBuilder().append(text);
        return complete();

    }

    @Override
    public Accessibility.ViewGroupAccessibility setContentDescription(CharSequence text) {
        return setContentDescription(text.toString());
    }

    @Override
    public ModifiableContentDescription setModifiableContentDescription(String text) {

        if (text != null) {
            this.isModified = true;
        }

        this.mainText = new StringBuilder().append(text);
        return this;

    }

    @Override
    public ModifiableContentDescription setModifiableContentDescription(CharSequence text) {
        return setModifiableContentDescription(text.toString());
    }

    @Override
    public Accessibility.ViewGroupAccessibility complete() {

        String contentDescription = (isModified)
                ? mainText.insert(0, prependText).append(appendText).toString()
                : mainText.toString();

        this.parentView.setContentDescription(contentDescription);

        return this.accessibility;

    }
}