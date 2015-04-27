package pl.turek.stacksearch.core;

import android.app.Application;

import pl.turek.stacksearch.net.StackSearchConnectivityManager;

/**
 * @author Krzysztof Turek (2015-04-27).
 */
public class StackSearchApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        StackSearchConnectivityManager.getInstance(this);
    }
}
