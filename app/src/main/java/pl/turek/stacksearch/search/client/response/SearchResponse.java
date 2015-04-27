package pl.turek.stacksearch.search.client.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Krzysztof Turek (2015-04-28).
 */
public class SearchResponse {

    @SuppressWarnings("unused")
    @SerializedName("items")
    private List<Question> mQuestions;

    @SuppressWarnings("unused")
    @SerializedName("error_id")
    private int mErrorId;

    @SuppressWarnings("unused")
    @SerializedName("error_message")
    private String mErrorMsg;

    @SuppressWarnings("unused")
    @SerializedName("error_name")
    private String mErrorName;

    public List<Question> getQuestions() {
        return mQuestions;
    }

    public int getErrorId() {
        return mErrorId;
    }

    public String getErrorMessage() {
        return mErrorMsg;
    }

    public String getErrorName() {
        return mErrorName;
    }
}
