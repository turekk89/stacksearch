package pl.turek.stacksearch.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import pl.turek.stacksearch.ui.BaseContentActivity;
import pl.turek.stacksearch.ui.BaseFragment;


public class SearchActivity extends BaseContentActivity {

    public static final String EXTRA_SEARCH_PHRASE = "extra_search_phrase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BaseFragment onCreateFragment() {
        return SearchFragment.newInstance();
    }

    @Override
    protected void onBeforeCreateFragment(@Nullable Bundle saveInstanceState) {
        if (saveInstanceState == null) {
            // Create retained fragment before normal one returned
            // from onCreateFragment() method
            createSearchTaskRetainedFragment();
        }
    }

    private void createSearchTaskRetainedFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(SearchTaskRetainedFragment.newInstance(),
                SearchTaskRetainedFragment.FRAGMENT_TAG).commit();
    }

}
