package pl.turek.stacksearch.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import pl.turek.stacksearch.R;
import pl.turek.stacksearch.net.event.NetworkAvailabilityChangedEvent;
import pl.turek.stacksearch.ui.BaseFragment;

/**
 * @author Krzysztof Turek (2015-04-27).
 */
public class SearchFragment extends BaseFragment {

    @InjectView(R.id.search_button)
    Button mSearchButton;

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
        //TODO
    }

    @SuppressWarnings("unused")
    public void onEventMainThread(final NetworkAvailabilityChangedEvent event) {
        mSearchButton.setEnabled(event.isNetworkAvailable());
    }
}
