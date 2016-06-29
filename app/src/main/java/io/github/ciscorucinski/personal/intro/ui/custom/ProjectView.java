package io.github.ciscorucinski.personal.intro.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.ciscorucinski.personal.intro.R;
import io.github.ciscorucinski.personal.intro.model.Resume;

/**
 * TODO: document your custom view class.
 */
public class ProjectView extends RelativeLayout implements Mappable<Resume.Project> {

    private static final int MAX_ACCOMPLISHMENTS = 3;
    private static final String EMPTY_LIST_ITEM = "";

    private String projectName;
    private String projectLocation;
    private String projectLink;
    private String projectYear;
    private String projectDescription;

    private List<String> projectAccomplishments;

    private TextView txtProjectName;
    private TextView txtProjectLocation;
    private TextView txtProjectLink;
    private TextView txtProjectYear;
    private TextView txtProjectDescription;

    private TextView txtAccomplishment1;
    private TextView txtAccomplishment2;
    private TextView txtAccomplishment3;

    public ProjectView(Context context) {

        super(context);
        init(null, 0);
    }

    public ProjectView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(attrs, 0);
    }

    public ProjectView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public static ProjectView populate(Context context, Resume.Project data) {

        ProjectView view = new ProjectView(context);
        view.populate(data);

        return view;

    }

    private void init(AttributeSet attrs, int defStyle) {

        ViewGroup root = (ViewGroup) LayoutInflater.from(getContext()).inflate(
                R.layout.internal_project_view, this, true);

        projectAccomplishments = new ArrayList<>(MAX_ACCOMPLISHMENTS);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ProjectView, defStyle, 0);

        projectName = a.getString(R.styleable.ProjectView_projectName);
        projectLocation = a.getString(R.styleable.ProjectView_projectLocation);
        projectLink = a.getString(R.styleable.ProjectView_projectLink);
        projectYear = a.getString(R.styleable.ProjectView_projectYear);
        projectDescription = a.getString(R.styleable.ProjectView_projectDescription);

        if (a.hasValue(R.styleable.ProjectView_projectAccomplishment1)) {
            projectAccomplishments.add(a.getString(R.styleable.ProjectView_projectAccomplishment1));
        }
        if (a.hasValue(R.styleable.ProjectView_projectAccomplishment2)) {
            projectAccomplishments.add(a.getString(R.styleable.ProjectView_projectAccomplishment2));
        }
        if (a.hasValue(R.styleable.ProjectView_projectAccomplishment3)) {
            projectAccomplishments.add(a.getString(R.styleable.ProjectView_projectAccomplishment3));
        }
        a.recycle();

        txtProjectName = (TextView) root.findViewById(R.id.project_textview_name);
        txtProjectLocation = (TextView) root.findViewById(R.id.project_textview_location);
        txtProjectLink = (TextView) root.findViewById(R.id.project_textview_link);
        txtProjectYear = (TextView) root.findViewById(R.id.project_textview_year);
        txtProjectDescription = (TextView) root.findViewById(R.id.project_textview_description);

        txtAccomplishment1 = (TextView) root.findViewById(R.id.project_textview_accomplishment1);
        txtAccomplishment2 = (TextView) root.findViewById(R.id.project_textview_accomplishment2);
        txtAccomplishment3 = (TextView) root.findViewById(R.id.project_textview_accomplishment3);

        invalidateView();

    }

    @Override
    public void populate(Resume.Project data) {

        projectName = data.name();
        projectLocation = data.location();
        projectLink = (data.link() == null) ? ("") : (data.link().toString());
        projectYear = data.year();
        projectDescription = data.description();
        projectAccomplishments.add(data.accomplishment1());
        projectAccomplishments.add(data.accomplishment2());
        projectAccomplishments.add(data.accomplishment3());

        invalidateView();

    }

    private void invalidateView() {

        txtProjectName.setText(projectName);
        txtProjectLocation.setText(projectLocation);
        txtProjectLink.setText(projectLink);
        txtProjectYear.setText(projectYear);
        txtProjectDescription.setText(projectDescription);

        if ("".equals(projectLink)) txtProjectLink.setVisibility(GONE);
        else txtProjectLink.setVisibility(VISIBLE);

        // This will get the zero-based index of the first empty item. That happens to also be
        // the number of visible items; except when there are no empty items or the list is empty;
        // from which a -1 is returned
        int numberOfVisibleItems = projectAccomplishments.indexOf(EMPTY_LIST_ITEM);

        // If the list is empty, then fix this variable to show 0
        if (projectAccomplishments.size() == 0) numberOfVisibleItems = 0;

        showAccomplishments(numberOfVisibleItems);

    }

    private void showAccomplishments(int numberOfVisibleItems) {

        txtAccomplishment1.setVisibility(GONE);
        txtAccomplishment2.setVisibility(GONE);
        txtAccomplishment3.setVisibility(GONE);

        switch (numberOfVisibleItems) {

            case -1: // do nothing. Fall through
            case 3:

                txtAccomplishment3.setVisibility(VISIBLE);
                setAccomplishmentText(txtAccomplishment3, projectAccomplishments.get(2));

            case 2:

                txtAccomplishment2.setVisibility(VISIBLE);
                setAccomplishmentText(txtAccomplishment2, projectAccomplishments.get(1));

            case 1:

                txtAccomplishment1.setVisibility(VISIBLE);
                setAccomplishmentText(txtAccomplishment1, projectAccomplishments.get(0));
                break;

            case 0:
                break; // do nothing
            default:
                break;

        }

    }

    private void setAccomplishmentText(TextView textView, String text) {

        textView.setText(String.format("%s %s", "-", text));

    }

    public String getProjectName() {

        return projectName;
    }

    public void setProjectName(String projectName) {

        this.projectName = projectName;
        invalidateView();
    }

    public String getProjectLocation() {

        return projectLocation;
    }

    public void setProjectLocation(String projectLocation) {

        this.projectLocation = projectLocation;
        invalidateView();
    }

    public String getProjectLink() {

        return projectLink;
    }

    public void setProjectLink(String projectLink) {

        this.projectLink = projectLink;
        invalidateView();
    }

    public String getProjectYear() {

        return projectYear;
    }

    public void setProjectYear(String projectYear) {

        this.projectYear = projectYear;
        invalidateView();
    }

    public String getProjectDescription() {

        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {

        this.projectDescription = projectDescription;
        invalidateView();
    }

    public List<String> getProjectAccomplishments() {

        return projectAccomplishments;
    }

    public void setProjectAccomplishments(final List<String> projectAccomplishments) {

        List<String> list = projectAccomplishments;

        // Verify that there are at least MAX_ACCOMPLISHMENTS string values in the list. The list can contain all empty strings.
        while (list.size() < MAX_ACCOMPLISHMENTS) {

            list.add(EMPTY_LIST_ITEM);

        }

        // Verify that the are no more than MAX_ACCOMPLISHMENTS string values in the list. If the passed in list if longer, then only the first MAX_ACCOMPLISHMENTS will be used. The rest will be ignored
        if (list.size() > MAX_ACCOMPLISHMENTS) {

            list = list.subList(0, MAX_ACCOMPLISHMENTS);

        }

        this.projectAccomplishments = list;
        invalidateView();
    }

}
