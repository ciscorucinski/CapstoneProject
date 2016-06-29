package io.github.ciscorucinski.personal.intro.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.github.ciscorucinski.personal.intro.R;
import io.github.ciscorucinski.personal.intro.model.Resume;

/**
 * TODO: document your custom view class.
 */
public class EducationView extends RelativeLayout implements Mappable<Resume.Education> {

    private String educationName;
    private String educationLocation;

    private String educationDegree;
    private String educationYears;
    private String educationCourses;

    private TextView txtEducationName;
    private TextView txtEducationLocation;
    private TextView txtEducationDegree;
    private TextView txtEducationYears;
    private TextView txtEducationCourses;

    public EducationView(Context context) {

        super(context);
        init(null, 0);
    }

    public EducationView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(attrs, 0);
    }

    public EducationView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public static View populate(Context context, Resume.Education data) {

        EducationView view = new EducationView(context);
        view.populate(data);

        return view;

    }

    private void init(AttributeSet attrs, int defStyle) {

        ViewGroup root = (ViewGroup) LayoutInflater.from(getContext()).inflate(
                R.layout.internal_education_view, this, true);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.EducationView, defStyle, 0);

        educationName = a.getString(R.styleable.EducationView_educationName);
        educationLocation = a.getString(R.styleable.EducationView_educationLocation);
        educationDegree = a.getString(R.styleable.EducationView_educationDegree);
        educationYears = a.getString(R.styleable.EducationView_educationYears);
        educationCourses = a.getString(R.styleable.EducationView_educationCourse);

        a.recycle();

        txtEducationName = (TextView) root.findViewById(R.id.education_textview_name);
        txtEducationLocation = (TextView) root.findViewById(R.id.education_textview_location);
        txtEducationDegree = (TextView) root.findViewById(R.id.education_textview_degree);
        txtEducationYears = (TextView) root.findViewById(R.id.education_textview_years);
        txtEducationCourses = (TextView) root.findViewById(R.id.education_textview_course);

        // Update TextPaint and text measurements from attributes
        invalidateView();

    }

    @Override
    public void populate(Resume.Education data) {

        educationName = data.name();
        educationLocation = data.location();
        educationDegree = data.degree();
        educationYears = String.format("%s - %s", data.start_date(), data.end_date());
        educationCourses = data.courses();

        invalidateView();
    }

    private void invalidateView() {

        txtEducationName.setText(educationName);
        txtEducationLocation.setText(educationLocation);
        txtEducationDegree.setText(educationDegree);
        txtEducationYears.setText(educationYears);
        txtEducationCourses.setText(Html.fromHtml(String.format("<b>Courses -</b> %s", educationCourses)));

    }

    public String getEducationName() {

        return educationName;
    }

    public void setEducationName(String educationName) {

        this.educationName = educationName;
        invalidateView();
    }

    public String getEducationLocation() {

        return educationLocation;
    }

    public void setEducationLocation(String educationLocation) {

        this.educationLocation = educationLocation;
        invalidateView();
    }

    public String getEducationDegree() {

        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {

        this.educationDegree = educationDegree;
        invalidateView();
    }

    public String getEducationYears() {

        return educationYears;
    }

    public void setEducationYears(String educationYears) {

        this.educationYears = educationYears;
        invalidateView();
    }

    public String getEducationCourses() {

        return educationCourses;
    }

    public void setEducationCourses(String educationCourses) {

        this.educationCourses = educationCourses;
        invalidateView();
    }

}
