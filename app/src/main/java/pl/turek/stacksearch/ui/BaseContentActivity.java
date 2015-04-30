package pl.turek.stacksearch.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import pl.turek.stacksearch.R;
import pl.turek.stacksearch.net.event.NetworkAvailabilityChangedEvent;

/**
 * @author Krzysztof Turek (2015-04-27).
 */
public abstract class BaseContentActivity extends BaseFragmentActivity {

    private static final String KEY_CROUTON_VISIBLE = "key_crouton_visible";

    private static final int FRAGMENT_CONTAINER_ID = R.id.base_content_activity_fragment_container;
    private static final Configuration CONFIGURATION_INFINITE = new Configuration.Builder()
            .setDuration(Configuration.DURATION_INFINITE).build();

    private Crouton mCroutonAlert;

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_content_activity);
        ButterKnife.inject(this);

        onBeforeFragmentCreate(savedInstanceState);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            ViewCompat.setElevation(mToolbar, 0); // remove shadow for API 21 and higher
        }

        createFragmentIfNeeded(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.getBoolean(KEY_CROUTON_VISIBLE)) {
            makeAndShowCroutonAlert();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("unused")
    public void onEventMainThread(final NetworkAvailabilityChangedEvent event) {
        cancelCroutonAlert();
        if (!event.isNetworkAvailable()) {
            makeAndShowCroutonAlert();
        }
    }

    private void makeAndShowCroutonAlert() {
        final String alertText = getString(R.string.no_internet_connection);
        mCroutonAlert = Crouton.makeText(this, alertText, Style.ALERT, getFragmentContainerId());
        mCroutonAlert.setConfiguration(CONFIGURATION_INFINITE);
        mCroutonAlert.show();
    }

    private void cancelCroutonAlert() {
        Crouton.cancelAllCroutons();
        mCroutonAlert = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_CROUTON_VISIBLE, (mCroutonAlert != null));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.clearCroutonsForActivity(this);
    }

    @Override
    public int getFragmentContainerId() {
        return FRAGMENT_CONTAINER_ID;
    }

    protected void onBeforeFragmentCreate(@SuppressWarnings("unused") @Nullable Bundle saveInstanceState) {

    }
}
