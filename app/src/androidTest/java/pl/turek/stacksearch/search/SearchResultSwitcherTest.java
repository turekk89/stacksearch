package pl.turek.stacksearch.search;

import android.annotation.SuppressLint;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.LayoutInflater;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.turek.stacksearch.R;

@RunWith(AndroidJUnit4.class)
public class SearchResultSwitcherTest {

    private SearchResultSwitcher mSearchResultSwitcher;

    @Rule
    public ActivityTestRule<SearchActivity> mSearchActivityRule = new ActivityTestRule<>(SearchActivity.class);

    @SuppressLint("InflateParams")
    @Before
    public void setUp() {
        mSearchResultSwitcher = (SearchResultSwitcher) LayoutInflater.from(mSearchActivityRule.getActivity())
                .inflate(R.layout.search_result_fragment, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setUnsupportedMode() {
        final int unsupportedMode = mSearchResultSwitcher.getChildCount();
        SearchResultSwitcher.getMode(unsupportedMode);
    }

    @Test
    public void setLasSupportedMode() {
        final int lastSupportedMode = mSearchResultSwitcher.getChildCount() - 1;
        mSearchResultSwitcher.setMode(SearchResultSwitcher.getMode(lastSupportedMode));
    }

    @After
    public void tearDown() {
        mSearchResultSwitcher = null;
    }
}