package pl.turek.stacksearch.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import pl.turek.stacksearch.ui.BaseContentActivity;
import pl.turek.stacksearch.ui.BaseFragment;


public class SearchActivity extends BaseContentActivity {

    public static final String EXTRA_SEARCH_PHRASE = "extra_search_phrase";

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

    public void replaceFragment(final BaseFragment fragment) {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(getFragmentContainerId(), fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void createSearchTaskRetainedFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(SearchTaskRetainedFragment.newInstance(),
                SearchTaskRetainedFragment.FRAGMENT_TAG).commit();
    }

}
