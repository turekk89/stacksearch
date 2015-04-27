package pl.turek.stacksearch.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * @author Krzysztof Turek (2015-04-27).
 */
public class StackSearchConnectivityManager {

    private static volatile StackSearchConnectivityManager sInstance;
    private final Context mAppContext;
    private final ConnectivityManager mConnectivityManager;

    private StackSearchConnectivityManager(final Context context) {
        mAppContext = context.getApplicationContext();
        mConnectivityManager = (ConnectivityManager) mAppContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static StackSearchConnectivityManager getInstance(@NonNull final Context context) {
        if (sInstance == null) {
            synchronized (StackSearchConnectivityManager.class) {
                if (sInstance == null) {
                    sInstance = new StackSearchConnectivityManager(context);
                    sInstance.registerNetworkChangedReceiver();
                }
            }
        }
        return sInstance;
    }

    public boolean isNetworkAvailable() {
        final NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    private final BroadcastReceiver mNetworkStateChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                final Bundle bundle = intent.getExtras();

                final boolean connected;
                if (bundle != null) {
                    connected = !bundle.getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY);
                } else {
                    connected = isNetworkAvailable();
                }

                Toast.makeText(mAppContext, "Network connection: " + connected, Toast.LENGTH_SHORT).show();

                //TODO
                // EventBuss
            }
        }
    };

    private void registerNetworkChangedReceiver() {
        mAppContext.registerReceiver(mNetworkStateChangedReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
}
