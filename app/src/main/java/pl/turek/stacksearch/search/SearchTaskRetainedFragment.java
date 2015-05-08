package pl.turek.stacksearch.search;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;

import pl.turek.stacksearch.search.client.SearchClient;
import pl.turek.stacksearch.search.client.response.SearchResponse;
import pl.turek.stacksearch.ui.BaseFragment;
import pl.turek.stacksearch.ui.BaseFragmentActivity;

/**
 * @author Krzysztof Turek (2015-04-29).
 */
public class SearchTaskRetainedFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = SearchTaskRetainedFragment.class.getSimpleName();

    private SearchAsyncTask mSearchAsyncTask;
    private SearchResponse mSearchResponse;
    private Callback mCallback;

    public interface Callback {
        void onPreSearch(final boolean refresh);

        void onPostSearch(final SearchResponse response);
    }

    public SearchTaskRetainedFragment() {
        setRetainInstance(true);
    }

    public static SearchTaskRetainedFragment newInstance() {
        return new SearchTaskRetainedFragment();
    }

    public static SearchTaskRetainedFragment getInstance(final Context context) {
        final FragmentManager fragmentManager = ((BaseFragmentActivity) context).getSupportFragmentManager();
        return (SearchTaskRetainedFragment) fragmentManager.findFragmentByTag(SearchTaskRetainedFragment.FRAGMENT_TAG);
    }

    public void search(final String searchPhrase, final boolean refresh) {
        if (mSearchAsyncTask == null || AsyncTask.Status.FINISHED == mSearchAsyncTask.getStatus()) {
            mSearchAsyncTask = new SearchAsyncTask(refresh);
            mSearchAsyncTask.execute(searchPhrase);
        }
    }

    public SearchResponse getSearchResponse() {
        return mSearchResponse;
    }

    public void setCallback(final Callback callback) {
        mCallback = callback;
    }


    private final class SearchAsyncTask extends AsyncTask<String, Void, SearchResponse> {

        final boolean mRefresh;

        public SearchAsyncTask(final boolean refresh) {
            mRefresh = refresh;
        }

        @Override
        protected void onPreExecute() {
            if (mCallback != null) {
                mCallback.onPreSearch(mRefresh);
            }
        }

        @Override
        protected SearchResponse doInBackground(String... params) {
            final String searchPhrase = params[0];
            final SearchClient searchClient = new SearchClient();
            return searchClient.search(searchPhrase);
        }

        @Override
        protected void onPostExecute(SearchResponse searchResponse) {
            mSearchResponse = searchResponse;

            if (mCallback != null) {
                mCallback.onPostSearch(searchResponse);
            }
        }
    }
}
