package pl.turek.stacksearch.search;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import pl.turek.stacksearch.R;
import pl.turek.stacksearch.net.event.NetworkAvailabilityChangedEvent;
import pl.turek.stacksearch.ui.BaseFragment;
import pl.turek.stacksearch.util.UIUtils;

/**
 * @author Krzysztof Turek (2015-04-27).
 */
public class SearchFragment extends BaseFragment {

    @InjectView(R.id.search_button)
    Button mSearchButton;
    @InjectView(R.id.search_query)
    EditText mSearchQueryEditText;

    public SearchFragment() {
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.search_fragment, container, false);
        ButterKnife.inject(this, root);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        UIUtils.showSoftKeyboard(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.reset(this);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.search_button)
    void search() {
        UIUtils.hideSoftKeyboard(getActivity());
        final String searchPhrase = getSearchPhrase();
        if (TextUtils.isEmpty(searchPhrase)) {
            showQueryError();
        } else {
            showSearchResult(searchPhrase);
        }
    }

    @SuppressWarnings("unused")
    public void onEventMainThread(final NetworkAvailabilityChangedEvent event) {
        mSearchButton.setEnabled(event.isNetworkAvailable());
    }

    private String getSearchPhrase() {
        return mSearchQueryEditText.getText().toString().trim();
    }

    private void showQueryError() {
        final String errorMsg = getString(R.string.search_activity_search_query_error);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            // workaround (hack)
            // https://code.google.com/p/android/issues/detail?id=22920 - bug
            final ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(android.R.color.primary_text_light));
            final SpannableStringBuilder spannableStringBuilder = SpannableStringBuilder.valueOf(errorMsg);
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorMsg.length(), 0);
            mSearchQueryEditText.setError(spannableStringBuilder);
        } else {
            mSearchQueryEditText.setError(errorMsg);
        }
    }

    private void showSearchResult(final String searchPhrase) {
        final SearchActivity searchActivity = (SearchActivity) getActivity();
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(searchActivity.getFragmentContainerId(), SearchResultFragment.newInstance(searchPhrase));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}
