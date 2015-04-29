package pl.turek.stacksearch.search;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

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

    private int mAvatarSize;

    public SearchResultListItemView(Context context) {
        this(context, null);
    }

    public SearchResultListItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchResultListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (isInEditMode()) return;

        ButterKnife.inject(this);
    }

    private void init() {
        mAvatarSize = getResources().getDimensionPixelSize(R.dimen.material_list_item_avatar_size);
    }

    public void setData(final Question question) {
        mShowNameTextView.setText(question.getOwnerName());
        mTitleTextView.setText(question.getTitle());
        mAnswersTextView.setText(String.format(getResources().
                getString(R.string.search_result_list_answer_count), question.getAnswerCount()));

        final RequestCreator requestCreator = Picasso.with(getContext()).load(question.getOwnerAvatarUri())
                .placeholder(R.drawable.ic_default_avatar).error(R.drawable.ic_default_avatar)
                .resize(mAvatarSize, mAvatarSize).centerCrop();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            requestCreator.noFade();
        }
        requestCreator.priority(Picasso.Priority.HIGH).into(mAvatarImageView);
    }
}
