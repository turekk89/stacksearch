package pl.turek.stacksearch.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Krzysztof Turek (2015-04-29).
 */
public class UIUtils {

    public static void hideSoftKeyboard(final Activity activity) {
        if (activity != null) {
            final Window activityWindow = activity.getWindow();
            if (activityWindow != null) {
                final InputMethodManager mgr = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(activityWindow.getDecorView().getWindowToken(), 0);
            }
        }
    }

    public static void showSoftKeyboard(final Context context, final View view) {
        if (context != null && view != null) {
            final InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
