package io.github.ciscorucinski.personal.intro.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.github.ciscorucinski.personal.intro.R;
import io.github.ciscorucinski.personal.intro.model.Resume;

@SuppressWarnings("unused")
public class ContributionView extends RelativeLayout implements Mappable<Resume.Contribution> {

    private String projectName;
    private String projectCreator;
    private String projectLink;
    private String contributionYear;
    private String contributionDescription;

    private TextView txtProjectName;
    private TextView txtProjectCreator;
    private TextView txtProjectLink;
    private TextView txtContributionYear;
    private TextView txtContributionDescription;

    public ContributionView(Context context) {

        super(context);
        init(null, 0);
    }

    public ContributionView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(attrs, 0);
    }

    public ContributionView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public static ContributionView populate(Context context, Resume.Contribution data) {

        ContributionView view = new ContributionView(context);
        view.populate(data);

        return view;

    }

    private void init(AttributeSet attrs, int defStyle) {

        ViewGroup root = (ViewGroup) LayoutInflater.from(getContext()).inflate(
                R.layout.internal_contribution_view, this, true);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ContributionView, defStyle, 0);

        projectName = a.getString(R.styleable.ContributionView_projectName);
        projectCreator = a.getString(R.styleable.ContributionView_projectCreator);
        projectLink = a.getString(R.styleable.ContributionView_projectLink);
        contributionYear = a.getString(R.styleable.ContributionView_contributionYear);
        contributionDescription = a.getString(R.styleable.ContributionView_contributionDescription);

        a.recycle();

        txtProjectName = (TextView) root.findViewById(R.id.person_textview_name);
        txtProjectCreator = (TextView) root.findViewById(R.id.contribution_textview_creator);
        txtProjectLink = (TextView) root.findViewById(R.id.contribution_textview_link);
        txtContributionYear = (TextView) root.findViewById(R.id.contribution_textview_year);
        txtContributionDescription = (TextView) root.findViewById(R.id.person_textview_objective);

        // Update TextPaint and text measurements from attributes
        invalidateView();

    }

    public void populate(Resume.Contribution data) {

        projectName = data.project();
        projectCreator = data.creator();
        projectLink = data.uri();
        contributionYear = data.year();
        contributionDescription = data.description();

        invalidateView();

    }

    private void invalidateView() {

        txtProjectName.setText(projectName);
        txtProjectCreator.setText(String.format("By %s", projectCreator));
        txtProjectLink.setText(projectLink);
        txtContributionYear.setText(contributionYear);
        txtContributionDescription.setText(contributionDescription);

        if ("".equals(projectLink)) txtProjectLink.setVisibility(GONE);
        else txtProjectLink.setVisibility(VISIBLE);

    }

    public String getProjectName() {

        return projectName;
    }

    public void setProjectName(String projectName) {

        this.projectName = projectName;
        invalidateView();
    }

    public String getProjectCreator() {

        return projectCreator;
    }

    public void setProjectCreator(String projectCreator) {

        this.projectCreator = projectCreator;
        invalidateView();
    }

    public String getProjectLink() {

        return projectLink;
    }

    public void setProjectLink(String projectLink) {

        this.projectLink = projectLink;
        invalidateView();
    }

    public String getContributionYear() {

        return contributionYear;
    }

    public void setContributionYear(String contributionYear) {

        this.contributionYear = contributionYear;
        invalidateView();
    }

    public String getContributionDescription() {

        return contributionDescription;
    }

    public void setContributionDescription(String contributionDescription) {

        this.contributionDescription = contributionDescription;
        invalidateView();
    }

}
