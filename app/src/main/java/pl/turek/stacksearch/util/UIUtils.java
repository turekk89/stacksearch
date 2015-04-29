package pl.turek.stacksearch.util;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * @author Krzysztof Turek (2015-04-29).
 */
public class UIUtils {

    public static void hideSoftKeyboard(final Activity activity) {
        if (activity == null) return;

        final Window activityWindow = activity.getWindow();
        if (activityWindow != null) {
            final InputMethodManager mgr = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(activityWindow.getDecorView().getWindowToken(), 0);
        }
    }

    public static void showSoftKeyboard(Context context, TextView textView) {
        if (context != null && textView != null) {
            final InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            // only will trigger it if no physical keyboard is open
            mgr.showSoftInput(textView, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}