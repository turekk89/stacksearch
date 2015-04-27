package pl.turek.stacksearch.search.client;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import pl.turek.stacksearch.search.client.response.SearchResponse;

/**
 * @author Krzysztof Turek (2015-04-27).
 */
public class SearchClient {

    private static final String SEARCH_API_URL = "http://api.stackexchange.com";
    private static final String API_VERSION = "/2.2";
    private static final String METHOD = "/search?";
    private static final String ORDER = "order=desc&"; // or asc
    private static final String SORT = "sort=activity&"; // or votes, creation, relevance
    private static final String SEARCH = "%sintitle=%s";
    private static final String SITE = "&site=stackoverflow";


    public SearchResponse search(@NonNull final String searchPhrase) {
        final String requestUrl = buildRequestUrl(searchPhrase);

        final Request request = new Request.Builder().url(requestUrl).build();
        final Response response;
        SearchResponse searchResult = null;
        try {
            response = new OkHttpClient().newCall(request).execute();
            final Gson gson = new Gson();
            final Reader reader = new InputStreamReader(response.body().byteStream());
            searchResult = gson.fromJson(reader, SearchResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    private static String buildRequestUrl(final String searchPhrase) {
        return String.format(SEARCH, getPrefixUrl(), getSuffixUrl(searchPhrase));
    }

    private static String getPrefixUrl() {
        final StringBuilder stringUrlPrefixBuilder = new StringBuilder(128);
        stringUrlPrefixBuilder.append(SEARCH_API_URL).append(API_VERSION).append(METHOD).append(ORDER).append(SORT);
        return stringUrlPrefixBuilder.toString();
    }

    private static String getSuffixUrl(final String searchPhrase) {
        final StringBuilder stringUrlSuffixBuilder = new StringBuilder(128);
        String encodedSearchPhrase = null;
        try {
            encodedSearchPhrase = URLEncoder.encode(searchPhrase, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        stringUrlSuffixBuilder.append(encodedSearchPhrase).append(SITE);
        return stringUrlSuffixBuilder.toString();
    }
}
