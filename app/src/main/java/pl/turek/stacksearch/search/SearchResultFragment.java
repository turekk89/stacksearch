package pl.turek.stacksearch.search;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.turek.stacksearch.R;
import pl.turek.stacksearch.search.client.response.Question;
import pl.turek.stacksearch.search.client.response.SearchResponse;
import pl.turek.stacksearch.ui.BaseFragment;

/**
 * @author Krzysztof Turek (2015-04-29).
 */
public class SearchResultFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private static final String KEY_SEARCH_RESULT_SWITCHER_MODE = "key_search_result_switcher_mode";

    private SearchTaskRetainedFragment mSearchTaskRetainedFragment;
    private String mSearchPhrase;
    private SearchResultListAdapter mSearchResultListAdapter;

    @InjectView(R.id.search_result_switcher)
    SearchResultSwitcher mSearchResultSwitcher;
    @InjectView(R.id.search_result_list)
    ListView mSearchResultListView;

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

        final Activity activity = getActivity();
        mSearchResultListAdapter = new SearchResultListAdapter(activity);
        mSearchResultListView.setAdapter(mSearchResultListAdapter);
        mSearchResultListView.setOnItemClickListener(this);

        mSearchTaskRetainedFragment = SearchTaskRetainedFragment.getInstance(activity);
        mSearchTaskRetainedFragment.attach(this);

        final Bundle args = getArguments();
        mSearchPhrase = args.getString(SearchActivity.EXTRA_SEARCH_PHRASE);

        if (isSearchNeeded()) {
            mSearchTaskRetainedFragment.search(mSearchPhrase);
        } else {
            showResult(mSearchTaskRetainedFragment.getSearchResponse());
        }
    }

    private boolean isSearchNeeded() {
        final String lastSearchedPhrase = mSearchTaskRetainedFragment.getLastSearchedPhrase();
        return TextUtils.isEmpty(lastSearchedPhrase) || !mSearchPhrase.
                equalsIgnoreCase(lastSearchedPhrase);
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

    public void showResult(final SearchResponse searchResponse) {
        if (searchResponse != null) {
            final int errorId = searchResponse.getErrorId();
            if (errorId != 0) {
                Toast.makeText(getActivity(), errorId + ", " + searchResponse.getErrorName(), Toast.LENGTH_SHORT)
                        .show();
                //TODO error dialog
            } else {
                mSearchResultListAdapter.setQuestions(searchResponse.getQuestions());
                mSearchResultSwitcher.setMode(SearchResultSwitcher.MODE_RESULT);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Question question = (Question) parent.getItemAtPosition(position);
        final String detailsLink = question.getDetailsLink();
        if (!TextUtils.isEmpty(detailsLink)) {
            final Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(detailsLink));
            startActivity(browserIntent);
        } else {
            Toast.makeText(getActivity(), R.string.search_result_list_details_error, Toast.LENGTH_SHORT).show();
        }
    }
}
