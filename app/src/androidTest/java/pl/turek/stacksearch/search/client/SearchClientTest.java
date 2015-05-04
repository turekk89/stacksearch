package pl.turek.stacksearch.search.client;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class SearchClientTest {

    private static final String EXPECTED_URL = "http://api.stackexchange.com/2.2/search?order=" +
            "desc&sort=activity&intitle=android&site=stackoverflow";

    @Test(expected = IllegalArgumentException.class)
    public void buildRequestUrlWithEmptySearchPhrase() {
        SearchClient.buildRequestUrl("");
    }

    @Test
    public void buildRequestUrlWithNotEmptySearchPhrase() {
        final String searchPhrase = "android";
        assertEquals(SearchClient.buildRequestUrl(searchPhrase), EXPECTED_URL);
    }
}