package pl.turek.stacksearch.search;

import android.content.Context;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.widget.ViewAnimator;

/**
 * @author Krzysztof Turek (2015-04-29).
 */
public class SearchResultSwitcher extends ViewAnimator {

    @IntDef({MODE_PROGRESS, MODE_RESULT})
    public @interface ViewMode {
    }

    public static final int MODE_PROGRESS = 0;
    public static final int MODE_RESULT = 1;

    public SearchResultSwitcher(Context context) {
        super(context);
    }

    public SearchResultSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMode(@ViewMode final int mode) {
        setDisplayedChild(mode);
    }
}
