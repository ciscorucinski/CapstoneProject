package io.github.ciscorucinski.personal.intro.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import io.github.ciscorucinski.personal.intro.R;
import io.github.ciscorucinski.personal.intro.ui.fragments.ContributionsFragment;
import io.github.ciscorucinski.personal.intro.ui.fragments.EducationFragment;
import io.github.ciscorucinski.personal.intro.ui.fragments.IntroductionFragment;
import io.github.ciscorucinski.personal.intro.ui.fragments.ProjectsFragment;
import io.github.ciscorucinski.personal.intro.ui.fragments.SimplifiedLoaderFragment;
import io.github.ciscorucinski.personal.intro.ui.fragments.WorkFragment;
import io.github.ciscorucinski.personal.intro.ui.helper.CustomTabActivityHelper;
import timber.log.Timber;


public class ResumeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String CREATE_INTENT_BUNDLE = "create_intent_bundle";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String SEEKING = "seeking";
    public static final String GITHUB = "github";
    public static final String LINKEDIN = "linkedin";
    private static final String IS_BOTTOM_SHEET_CLOSED = "isBottomSheetClosed";
    private long person_id;

    private CustomTabActivityHelper customTabHelper;
    private SimplifiedLoaderFragment currentFragment;
    private NavigationView navigationView;

    public static Intent createIntent(Context context) {

        return new Intent(context, ResumeActivity.class);

    }

    public static Intent createIntent(Context context, Bundle bundle) {

        Intent intent = new Intent(context, ResumeActivity.class);
        intent.putExtra(CREATE_INTENT_BUNDLE, bundle);

        return intent;

    }

    @SuppressWarnings("SameParameterValue")
    public static Intent createFlagIntent(Context context, Bundle bundle, int... flags) {

        Intent intent = createIntent(context, bundle);

        for (int flag : flags) {
            intent.setFlags(flag);
        }

        return intent;

    }

//    private static void copy(InputStream in, File dst) throws IOException {
//
//        FileOutputStream out = new FileOutputStream(dst);
//
//        byte[] buf = new byte[1024];
//        int len;
//
//        while ((len = in.read(buf)) > 0) {
//            out.write(buf, 0, len);
//        }
//
//        in.close();
//        out.close();
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUiFramework();

        Intent activityLaunchIntent = getIntent();

        // Now that the UI is there, set the text to the needed items
        if (activityLaunchIntent != null) {

            Bundle bundle = activityLaunchIntent.getBundleExtra(CREATE_INTENT_BUNDLE);
            boolean wideDisplay = getResources().getBoolean(R.bool.wide_display);

            person_id = bundle.getLong(ID);

            Timber.i("Loading Introduction Fragment with id (%s)", person_id);
            if (savedInstanceState == null) {

                Timber.i("New instance of this fragment");
                if (wideDisplay) {

                    if (currentFragment == null) currentFragment = ProjectsFragment.newInstance();

                    Timber.i("...on a wide display");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_intro_on_tablet,
                                    IntroductionFragment.newUnchangingInstance(person_id))
                            .replace(R.id.fragment_container,
                                    currentFragment)
                            .commit();

                    navigationView.setCheckedItem(R.id.projects);


                } else {

                    if (currentFragment == null)
                        currentFragment = IntroductionFragment.newInstance(person_id);

                    Timber.i("...on a narrow display");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, currentFragment)
                            .commit();

                    navigationView.setCheckedItem(R.id.introduction);

                }

            } else {
                Timber.i("Old instance of this fragment. Fragment will automatically be restored!");
            }

            // Grab the information from the bundles

            String name = bundle.getString(NAME);
            String seeking = bundle.getString(SEEKING);
            String emailAddress = bundle.getString(EMAIL);
            String phoneNumber = bundle.getString(PHONE);
            String phoneNumberFormatted = phoneNumber != null
                    ? phoneNumber.replace("-", " - ")
                    : "";

            String github_url = bundle.getString(GITHUB);
            String linkedin_url = bundle.getString(LINKEDIN);

            // Get references to all the needed Text fields and menu items

            TextView txtName = (TextView) navigationView.getHeaderView(0)
                    .findViewById(R.id.navigation_header_name);
            TextView txtSeeking = (TextView) navigationView.getHeaderView(0)
                    .findViewById(R.id.navigation_header_position);

            MenuItem menuEmail = navigationView.getMenu().findItem(R.id.email);
            MenuItem menuPhone = navigationView.getMenu().findItem(R.id.phone);
            MenuItem menuGithub = navigationView.getMenu().findItem(R.id.github);
            MenuItem menuLinkedIn = navigationView.getMenu().findItem(R.id.linkedin);

            // Set the text of all the needed navigation components
            txtName.setText(name);
            txtSeeking.setText(seeking);
            menuEmail.setTitle(emailAddress);
            menuPhone.setTitle(phoneNumberFormatted);

            // Create the Intent automatically for when the menu item is clicked

            menuEmail.setIntent(createEmailIntent(emailAddress));
            menuPhone.setIntent(createPhoneIntent(phoneNumber));

            // Cannot create an intent to open the URL with Chrome Custom Tabs. Need to create
            // empty Intents with a url bundle attached. That bundle can be read later to create the
            // Chrome Custom Tab Intent
            menuGithub.setIntent(new Intent().putExtra(GITHUB, github_url));
            menuLinkedIn.setIntent(new Intent().putExtra(LINKEDIN, linkedin_url));

        }

    }

    private void initializeUiFramework() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);

        ImageView headerImage = (ImageView) findViewById(R.id.header);

        // Async the image load into the Toolbar
        Picasso.with(this)
                .load(R.drawable.cheonggyecheon_hd)
                .resize(900, 900)
                .centerCrop()
                .placeholder(R.drawable.side_nav_bar)
                .into(headerImage);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        if (drawer != null) drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) navigationView.setNavigationItemSelectedListener(this);

        customTabHelper = new CustomTabActivityHelper();

        initializeBottomSheet();

    }

    private void initializeBottomSheet() {

        View bottomSheet = findViewById(R.id.bottom_sheet_introduction);

        if (bottomSheet == null) return;

        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);

        boolean isBottomSheetClosed = getPreferences(MODE_PRIVATE)
                .getBoolean(IS_BOTTOM_SHEET_CLOSED, false);

        if (isBottomSheetClosed) {

            bottomSheet.setVisibility(View.GONE);

        } else {

            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {

                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {

                        getPreferences(MODE_PRIVATE).edit()
                                .putBoolean(IS_BOTTOM_SHEET_CLOSED, true)
                                .apply();

                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });

        }

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        boolean isSinglePerson = getResources().getBoolean(R.bool.is_single_person);

        // If this app is only for 1 individual or business, then don't allow selection of
        // another resume. They are only suppose to see the one predefined resume I selected.
        if (isSinglePerson) return true;

        // Otherwise, inflate the menu that allows selection of another resume
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

//	@Override
//	protected void onRestoreInstanceState(Bundle savedInstanceState) {
//
//		boolean wideDisplay = getResources().getBoolean(R.bool.wide_display);
//
//		if (savedInstanceState != null) {
//
//			// Some other form of restoration has occurred. Get the previous displayed fragment
//			currentFragment = (SimplifiedLoaderFragment) getSupportFragmentManager()
//					.getFragment(savedInstanceState, CURRENT_FRAGMENT);
//
//		}
//
//		super.onRestoreInstanceState(savedInstanceState);
//	}
//
//	@Override
//	public void onSaveInstanceState(Bundle outState) {
//
//		getSupportFragmentManager().putFragment(outState, CURRENT_FRAGMENT, currentFragment);
//		super.onSaveInstanceState(outState);
//	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_select_resume) {

            // Don't call finish: This allows a cancel button click on the next activity to come
            // back to this activity
            startActivity(SelectorActivity.createIntent(this));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openFragment(SimplifiedLoaderFragment fragment) {

        currentFragment = fragment;

        Timber.i("loading a new fragment into view - %s", fragment);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragment_container, fragment)
                .commit();

        if (getSupportActionBar() != null) getSupportActionBar().setTitle(fragment.getName());

    }

    private void callPhone(Intent phoneIntent) {

        try {

            startActivity(Intent.createChooser(phoneIntent, "Call number..."));
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ResumeActivity.this, "There is no phone client installed.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private Intent createPhoneIntent(String phoneNumber) {

        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        Uri email = Uri.fromParts("tel", phoneNumber, null);

        phoneIntent.setData(email);
        phoneIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        return phoneIntent;

    }

    private Intent createEmailIntent(String emailAddress) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

        emailIntent.setData(Uri.parse(String.format("mailto:%s", emailAddress)).buildUpon()
                .appendQueryParameter("subject", "Your Hired!")
                .appendQueryParameter("to", emailAddress)
                .build());

        return emailIntent;

    }

    private void sendEmail(Intent emailIntent) {

        try {

            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ResumeActivity.this, "There is no email client installed.",
                    Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {

            case R.id.introduction:
                openFragment(IntroductionFragment.newInstance(person_id));
                break;

//			case R.id.skills:           openFragment(SkillsFragment.newInstance()); break;
            case R.id.projects:
                openFragment(ProjectsFragment.newInstance());
                break;
            case R.id.contributions:
                openFragment(ContributionsFragment.newInstance());
                break;
            case R.id.education:
                openFragment(EducationFragment.newInstance());
                break;
            case R.id.work:
                openFragment(WorkFragment.newInstance());
                break;

            case R.id.resume:
                openPDF("resume/resume.pdf");
                break;
            case R.id.github:
                openTab(Uri.parse(item.getIntent().getStringExtra(GITHUB)));
                break;
            case R.id.linkedin:
                openTab(Uri.parse(item.getIntent().getStringExtra(LINKEDIN)));
                break;

            // These are now handled via setIntent when the activity is created
            case R.id.email:
                sendEmail(item.getIntent());
                break;
            case R.id.phone:
                callPhone(item.getIntent());
                break;

            default: // do nothing

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @SuppressWarnings("SameParameterValue")
    private void openPDF(String filename) {

        final String auth = "io.github.ciscorucinski.personal.intro.file";
        final Uri content_uri = Uri.parse("content://" + auth);

        Uri pdf_uri = content_uri.buildUpon()
//				.appendPath(PdfProvider.getUriPrefix(auth))
                .appendPath(filename)
                .build();

        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);

        pdfIntent.setDataAndType(pdf_uri, "application/pdf");
        pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(ResumeActivity.this, "There is no PDF file.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void openTab(Uri uri) {

        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
        CustomTabActivityHelper
                .openCustomTab(this, customTabsIntent, uri,
                        new CustomTabActivityHelper.CustomTabFallback() {

                            @Override
                            public void openUri(Activity activity, Uri uri) {

                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);

                            }
                        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        customTabHelper.bindCustomTabsService(this);

    }

    @Override
    protected void onStop() {

        super.onStop();
        customTabHelper.unbindCustomTabsService(this);

    }

}
