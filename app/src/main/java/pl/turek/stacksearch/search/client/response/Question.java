package pl.turek.stacksearch.search.client.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author Krzysztof Turek (2015-04-28).
 */
public class Question {

    @SuppressWarnings("unused")
    @SerializedName("title")
    private String mTitle;

    @SuppressWarnings("unused")
    @SerializedName("link")
    private String mDetailsLink;

    @SuppressWarnings("unused")
    @SerializedName("answer_count")
    private int mAnswerCount;

    @SuppressWarnings("unused")
    @SerializedName("owner")
    private Owner mOwner;

    public String getTitle() {
        return mTitle;
    }

    public String getDetailsLink() {
        return mDetailsLink;
    }

    public int getAnswerCount() {
        return mAnswerCount;
    }

    public String getOwnerName() {
        return mOwner.getName();
    }

    public String getOwnerAvatarUri() {
        return mOwner.getAvatarUri();
    }

    static class Owner {
        @SuppressWarnings("unused")
        @SerializedName("display_name")
        private String mName;

        @SuppressWarnings("unused")
        @SerializedName("profile_image")
        private String mAvatarUri;

        // performance tip http://developer.android.com/training/articles/perf-tips.html

        /*package*/ String getName() {
            return mName;
        }

        /*package*/ String getAvatarUri() {
            return mAvatarUri;
        }
    }
}
