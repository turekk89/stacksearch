package pl.turek.stacksearch.search;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.turek.stacksearch.R;
import pl.turek.stacksearch.search.client.response.Question;

/**
 * @author Krzysztof Turek (2015-04-29).
 */
public class SearchResultListItemView extends RelativeLayout {

    @InjectView(R.id.avatar)
    ImageView mAvatarImageView;
    @InjectView(R.id.showName)
    TextView mShowNameTextView;
    @InjectView(R.id.title)
    TextView mTitleTextView;
    @InjectView(R.id.answers)
    TextView mAnswersTextView;

    public SearchResultListItemView(Context context) {
        this(context, null);
    }

    public SearchResultListItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchResultListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (isInEditMode()) return;

        ButterKnife.inject(this);
    }

    public void setData(final Question question) {
        //TODO
    }
}
