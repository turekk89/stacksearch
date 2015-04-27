package pl.turek.stacksearch.net.event;

/**
 * @author Krzysztof Turek (2015-04-27).
 */
public class NetworkAvailabilityChangedEvent {

    private final boolean mNetworkAvailable;

    public NetworkAvailabilityChangedEvent(final boolean networkAvailable) {
        mNetworkAvailable = networkAvailable;
    }

    public boolean isNetworkAvailable() {
        return mNetworkAvailable;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(64);
        builder.append(getClass().getSimpleName()).append(" [");
        builder.append("mNetworkAvailable=").append(mNetworkAvailable);
        builder.append("]");
        return builder.toString();
    }
}
