package pl.turek.stacksearch.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import pl.turek.stacksearch.R;

/**
 * @author Krzysztof Turek (2015-04-27).
 */
public abstract class BaseContentActivity extends BaseFragmentActivity {

    private static final int FRAGMENT_CONTAINER_ID = R.id.base_content_activity_fragment_container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_content_activity);

        createFragmentIfNeeded(savedInstanceState);
    }

    @Override
    public int getFragmentContainerId() {
        return FRAGMENT_CONTAINER_ID;
    }
}
