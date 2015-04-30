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

    private static final String KEY_SEARCH_BUTTON_ENABLED = "key_search_button_enabled";
    private static final String KEY_SEARCH_QUERY_ENABLED = "key_search_query_enabled";

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

        if (savedInstanceState != null) {
            mSearchButton.setEnabled(savedInstanceState.getBoolean(KEY_SEARCH_BUTTON_ENABLED));
            mSearchQueryEditText.setEnabled(savedInstanceState.getBoolean(KEY_SEARCH_QUERY_ENABLED));
        }

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_SEARCH_BUTTON_ENABLED, mSearchButton.isEnabled());
        outState.putBoolean(KEY_SEARCH_QUERY_ENABLED, mSearchQueryEditText.isEnabled());
    }

    @SuppressWarnings("unused")
    public void onEventMainThread(final NetworkAvailabilityChangedEvent event) {
        final boolean enable = event.isNetworkAvailable();
        mSearchButton.setEnabled(enable);
        mSearchQueryEditText.setEnabled(enable);
    }

    private String getSearchPhrase() {
        return mSearchQueryEditText.getText().toString().trim();
    }

    private void showQueryError() {
        final String errorMsg = getString(R.string.search_activity_search_query_error);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            // workaround (hack)
            // https://code.google.com/p/android/issues/detail?id=22920 - bug
            // I see this first time in my life :D but I use emulator, I don't have mobile with
            // android 2.3.7 so I can't test it with real device

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
