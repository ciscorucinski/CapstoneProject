package io.github.ciscorucinski.personal.intro.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.ciscorucinski.personal.intro.R;
import io.github.ciscorucinski.personal.intro.model.Resume;

@SuppressWarnings("unused")
public class SkillsTextView extends LinearLayout implements Mappable<Resume.Skill> {

    private ViewGroup root;

    private String skillsType;
    private String skillsList;

    private TextView txtSkillType;
    private TextView txtSkillList;

    public SkillsTextView(Context context) {

        super(context);
        init(null, 0);

    }

    public SkillsTextView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(attrs, 0);

    }

    public SkillsTextView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        init(attrs, defStyle);

    }

    public static SkillsTextView populate(Context context, Resume.Skill data) {

        SkillsTextView view = new SkillsTextView(context);
        view.populate(data);

        return view;

    }

    private void init(AttributeSet attrs, int defStyle) {

        root = (ViewGroup) LayoutInflater.from(getContext()).inflate(
                R.layout.internal_skills_text_view, this, true);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.SkillsTextView, defStyle, 0);

        skillsList = a.getString(R.styleable.SkillsTextView_skillList);
        skillsType = a.getString(R.styleable.SkillsTextView_skillType);

        a.recycle();

        txtSkillList = (TextView) root.findViewById(R.id.skills_textview_list);
        txtSkillType = (TextView) root.findViewById(R.id.skills_textview_type);

        invalidateView();

    }

    @Override
    public void populate(Resume.Skill data) {

        // TODO Create a SKILLSVIEW that combines 3 SkillsTextView's (or any number)

        invalidateView();

    }

    private void invalidateView() {

        txtSkillList.setText(skillsList);
        txtSkillType.setText(skillsType);

    }

    public String getSkillList() {
        return skillsList;
    }

    public String getSkillType() {
        return skillsType;
    }

    public void setSkillsList(String skills) {

        skillsList = skills;
        invalidateView();

    }

    public void setSkillsType(String type) {

        skillsType = type;
        invalidateView();

    }

}
