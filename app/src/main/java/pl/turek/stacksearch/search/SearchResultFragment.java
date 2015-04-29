package pl.turek.stacksearch.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.turek.stacksearch.R;
import pl.turek.stacksearch.ui.BaseFragment;

/**
 * @author Krzysztof Turek (2015-04-29).
 */
public class SearchResultFragment extends BaseFragment {

    private static final String KEY_SEARCH_RESULT_SWITCHER_MODE = "key_search_result_switcher_mode";

    private SearchTaskRetainedFragment mSearchTaskRetainedFragment;
    private String mSearchPhrase;

    @InjectView(R.id.search_result_switcher)
    SearchResultSwitcher mSearchResultSwitcher;

    public SearchResultFragment() {
    }

    public static SearchResultFragment newInstance(final String searchPhrase) {
        final SearchResultFragment fragment = new SearchResultFragment();
        final Bundle args = new Bundle();
        args.putString(SearchActivity.EXTRA_SEARCH_PHRASE, searchPhrase);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.search_result_fragment, container, false);
        ButterKnife.inject(this, root);

        if (savedInstanceState != null) {
            final int modeInt = savedInstanceState.getInt(KEY_SEARCH_RESULT_SWITCHER_MODE);
            mSearchResultSwitcher.setMode(SearchResultSwitcher.getMode(modeInt));
        }

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mSearchTaskRetainedFragment = SearchTaskRetainedFragment.getInstance(getActivity());
        mSearchTaskRetainedFragment.attach(this);

        final Bundle args = getArguments();
        mSearchPhrase = args.getString(SearchActivity.EXTRA_SEARCH_PHRASE);

        if (savedInstanceState == null) {
            mSearchTaskRetainedFragment.search(mSearchPhrase);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_SEARCH_RESULT_SWITCHER_MODE, mSearchResultSwitcher.getMode());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.reset(this);
    }

    public void showProgress() {
        mSearchResultSwitcher.setMode(SearchResultSwitcher.MODE_PROGRESS);
    }

    public void showResult() {
        mSearchResultSwitcher.setMode(SearchResultSwitcher.MODE_RESULT);
    }

}
