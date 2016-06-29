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
public class PersonView extends RelativeLayout implements Mappable<Resume.People> {

    private String personName;
    private String personObjective;
    private String personMotto;
    private String personPhone;
    private String personEmail;

    private TextView txtPersonName;
    private TextView txtPersonObjective;
    private TextView txtPersonMotto;
    private TextView txtPersonPhone;
    private TextView txtPersonEmail;

    public PersonView(Context context) {

        super(context);
        init(null, 0);
    }

    public PersonView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(attrs, 0);
    }

    public PersonView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public static PersonView populate(Context context, Resume.People data) {

        PersonView view = new PersonView(context);
        view.populate(data);

        return view;

    }

    private void init(AttributeSet attrs, int defStyle) {

        ViewGroup root = (ViewGroup) LayoutInflater.from(getContext()).inflate(
                R.layout.internal_person_view, this, true);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.PersonView, defStyle, 0);

        personName = a.getString(R.styleable.PersonView_personName);
        personObjective = a.getString(R.styleable.PersonView_personObjective);
        personMotto = a.getString(R.styleable.PersonView_personMotto);
        personPhone = a.getString(R.styleable.PersonView_personPhone);
        personEmail = a.getString(R.styleable.PersonView_personEmail);

        a.recycle();

        txtPersonName = (TextView) root.findViewById(R.id.person_textview_name);
        txtPersonObjective = (TextView) root.findViewById(R.id.person_textview_objective);
        txtPersonMotto = (TextView) root.findViewById(R.id.person_textview_motto);
        txtPersonPhone = (TextView) root.findViewById(R.id.person_textview_phone);
        txtPersonEmail = (TextView) root.findViewById(R.id.person_textview_email);

        // Update TextPaint and text measurements from attributes
        invalidateView();

    }

    @Override
    public void populate(Resume.People data) {

        personName = data.name();
        personObjective = data.objective();
        personMotto = data.motto();
        personPhone = data.phone();
        personEmail = data.email();

        invalidateView();

    }

    private void invalidateView() {

        txtPersonName.setText(personName);
        txtPersonObjective.setText(personObjective);
        txtPersonMotto.setText(personMotto);
        txtPersonPhone.setText(personPhone);
        txtPersonEmail.setText(personEmail);

    }

    public String getPersonName() {

        return personName;
    }

    public void setPersonName(String personName) {

        this.personName = personName;
        invalidateView();

    }

    public String getPersonObjective() {

        return personObjective;
    }

    public void setPersonObjective(String personObjective) {

        this.personObjective = personObjective;
        invalidateView();

    }

    public String getPersonMotto() {

        return personObjective;
    }

    public void setPersonMotto(String personMotto) {

        this.personMotto = personMotto;
        invalidateView();

    }

    public String getPersonPhone() {

        return personPhone;
    }

    public void setPersonPhone(String personPhone) {

        this.personPhone = personPhone;
        invalidateView();

    }

    public String getPersonEmail() {

        return personEmail;
    }

    public void setPersonEmail(String personEmail) {

        this.personEmail = personEmail;
        invalidateView();

    }
}
