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

/**
 * TODO: document your custom view class.
 */
public class WorkView extends RelativeLayout implements Mappable<Resume.Work> {

    private String workName;
    private String workLocation;
    private String workPosition;
    private String workYears;

    private TextView txtWorkName;
    private TextView txtWorkLocation;
    private TextView txtWorkPosition;
    private TextView txtWorkYears;

    public WorkView(Context context) {

        super(context);
        init(null, 0);
    }

    public WorkView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(attrs, 0);
    }

    public WorkView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public static WorkView populate(Context context, Resume.Work data) {

        WorkView view = new WorkView(context);
        view.populate(data);

        return view;

    }

    private void init(AttributeSet attrs, int defStyle) {

        ViewGroup root = (ViewGroup) LayoutInflater.from(getContext()).inflate(
                R.layout.internal_work_view, this, true);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.WorkView, defStyle, 0);

        workName = a.getString(R.styleable.WorkView_workName);
        workLocation = a.getString(R.styleable.WorkView_workLocation);
        workPosition = a.getString(R.styleable.WorkView_workPosition);
        workYears = a.getString(R.styleable.WorkView_workYears);

        a.recycle();

        txtWorkName = (TextView) root.findViewById(R.id.work_textview_name);
        txtWorkLocation = (TextView) root.findViewById(R.id.work_textview_location);
        txtWorkPosition = (TextView) root.findViewById(R.id.work_textview_position);
        txtWorkYears = (TextView) root.findViewById(R.id.work_textview_years);

        // Update TextPaint and text measurements from attributes
        invalidateView();

    }

    @Override
    public void populate(Resume.Work data) {

        workName = data.name();
        workLocation = data.location();
        workPosition = data.position();
        workYears = String.format("%s - %s", data.start_date(), data.end_date());

        invalidateView();

    }

    private void invalidateView() {

        txtWorkName.setText(workName);
        txtWorkLocation.setText(workLocation);
        txtWorkPosition.setText(workPosition);
        txtWorkYears.setText(workYears);

    }

    public String getWorkName() {

        return workName;
    }

    public void setWorkName(String workName) {

        this.workName = workName;
        invalidateView();
    }

    public String getWorkLocation() {

        return workLocation;
    }

    public void setWorkLocation(String workLocation) {

        this.workLocation = workLocation;
        invalidateView();
    }

    public String getWorkPosition() {

        return workPosition;
    }

    public void setWorkPosition(String workPosition) {

        this.workPosition = workPosition;
        invalidateView();
    }

    public String getWorkYears() {

        return workYears;
    }

    public void setWorkYears(String workYears) {

        this.workYears = workYears;
        invalidateView();
    }

}
