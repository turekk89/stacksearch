package pl.turek.stacksearch.search;

import android.os.Bundle;

import pl.turek.stacksearch.R;
import pl.turek.stacksearch.ui.BaseContentActivity;


public class SearchActivity extends BaseContentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
    }
}
