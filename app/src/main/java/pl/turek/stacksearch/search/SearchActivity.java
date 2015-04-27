package pl.turek.stacksearch.search;

import android.os.Bundle;

import pl.turek.stacksearch.ui.BaseContentActivity;
import pl.turek.stacksearch.ui.BaseFragment;


public class SearchActivity extends BaseContentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BaseFragment onCreateFragment() {
        return SearchFragment.newInstance();
    }
}
