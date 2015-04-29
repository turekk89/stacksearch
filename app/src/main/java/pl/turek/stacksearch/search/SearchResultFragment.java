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

    @InjectView(R.id.search_result_switcher)
    SearchResultSwitcher mSearchResultSwitcher;

    public SearchResultFragment() {
    }

    public static SearchResultFragment newInstance() {
        return new SearchResultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.search_result_fragment, container, false);
        ButterKnife.inject(this, root);

        mSearchResultSwitcher.setMode(SearchResultSwitcher.MODE_PROGRESS);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.reset(this);
    }
}
