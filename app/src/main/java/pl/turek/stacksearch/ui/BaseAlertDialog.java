package pl.turek.stacksearch.ui;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * @author Krzysztof Turek (2015-05-04).
 */
public abstract class BaseAlertDialog extends DialogFragment {

    private final String TAG = getClass().getSimpleName();

    protected AlertDialog.Builder mAlertDialogBuilder;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mAlertDialogBuilder = new AlertDialog.Builder(activity);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initAlertDialog();
        return mAlertDialogBuilder.create();
    }

    public String getCustomTag() {
        return TAG;
    }

    protected abstract void initAlertDialog();
}
