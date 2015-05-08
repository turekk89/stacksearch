package pl.turek.stacksearch.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import pl.turek.stacksearch.R;
import pl.turek.stacksearch.search.client.response.Question;

/**
 * @author Krzysztof Turek (2015-04-29).
 */
public class SearchResultListAdapter extends BaseAdapter {

    final List<Question> mQuestions = new ArrayList<>();
    final LayoutInflater mInflater;

    public SearchResultListAdapter(final Context context) {
        if (context == null) throw new NullPointerException("Context can not be null");

        mInflater = LayoutInflater.from(context);
    }

    public void setQuestions(final List<Question> questions) {
        mQuestions.clear();

        if (questions != null && !questions.isEmpty()) {
            mQuestions.addAll(questions);
        }

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mQuestions.size();
    }

    @Override
    public Question getItem(int position) {
        return mQuestions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.search_result_list_item_view, parent, false);
        }

        final Question question = getItem(position);

        final SearchResultListItemView itemView = ((SearchResultListItemView) convertView);
        itemView.setShowName(question.getOwnerName());
        itemView.setTitle(question.getTitle());
        itemView.setAnswerCount(question.getAnswerCount());
        itemView.setAvatar(question.getOwnerAvatarUri());

        return convertView;
    }
}
