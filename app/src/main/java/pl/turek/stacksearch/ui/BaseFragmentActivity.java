package pl.turek.stacksearch.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Krzysztof Turek (2015-04-27).
 */
public abstract class BaseFragmentActivity extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();


    protected void createFragmentIfNeeded(Bundle bundle) {
        if (bundle == null) createFragment();
    }

    private void createFragment() {
        final Fragment fragment = onCreateFragment();
        if (fragment == null) return;

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(getFragmentContainerId(), fragment);
        fragmentTransaction.commit();
    }

    protected int getFragmentContainerId() {
        return android.R.id.content;
    }

    protected abstract BaseFragment onCreateFragment();
}
