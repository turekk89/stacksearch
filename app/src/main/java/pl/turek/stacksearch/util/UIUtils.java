package pl.turek.stacksearch.util;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
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

    public static void showSoftKeyboard(final Activity activity) {
        if (activity != null) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }
}
