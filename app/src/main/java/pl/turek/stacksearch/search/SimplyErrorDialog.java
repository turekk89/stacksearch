package pl.turek.stacksearch.search;

import android.os.Bundle;

import pl.turek.stacksearch.ui.BaseAlertDialog;

/**
 * @author Krzysztof Turek (2015-05-04).
 */
public class SimplyErrorDialog extends BaseAlertDialog {

    private static final String KEY_ERROR_ID = "key_error_id";
    private static final String KEY_ERROR_MESSAGE = "key_error_message";
    private static final String KEY_ERROR_NAME = "key_error_name";

    public static SimplyErrorDialog newInstance(final int errorId, final String errorMsg, final String
            errorName) {
        final SimplyErrorDialog dialog = new SimplyErrorDialog();
        final Bundle args = new Bundle();
        args.putInt(KEY_ERROR_ID, errorId);
        args.putString(KEY_ERROR_MESSAGE, errorMsg);
        args.putString(KEY_ERROR_NAME, errorName);
        dialog.setArguments(args);
        return dialog;
    }


    @Override
    protected void initAlertDialog() {
        final Bundle args = getArguments();
        final int errorId = args.getInt(KEY_ERROR_ID);
        final String errorMsg = args.getString(KEY_ERROR_MESSAGE);
        final String errorName = args.getString(KEY_ERROR_NAME);

        mAlertDialogBuilder.setTitle(buildTitle(errorId, errorName));
        mAlertDialogBuilder.setMessage(errorMsg);
    }

    private static String buildTitle(final int errorId, final String errorName) {
        final StringBuilder titleBuilder = new StringBuilder(64);
        titleBuilder.append(errorName).append(" (").append(errorId).append(")");
        return titleBuilder.toString();
    }
}
