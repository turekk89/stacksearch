package pl.turek.stacksearch.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.turek.stacksearch.R;

/**
 * @author Krzysztof Turek (2015-04-27).
 */
public abstract class BaseContentActivity extends BaseFragmentActivity {

    private static final int FRAGMENT_CONTAINER_ID = R.id.base_content_activity_fragment_container;

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
    }

    @Override
    public int getFragmentContainerId() {
        return FRAGMENT_CONTAINER_ID;
    }

    protected void onBeforeFragmentCreate(@SuppressWarnings("unused") @Nullable Bundle saveInstanceState) {

    }
}
