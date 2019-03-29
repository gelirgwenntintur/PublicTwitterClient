package com.twitterclient.test.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    private static final Logger logger = Logger.getLogger(NetworkUtils.class);

    public static boolean isWIFINetworkAvailable(Context context) {
        logger.debug("isWIFINetworkAvailable");
        NetworkInfo info = getConnectivityManager(context).getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info != null && info.getState() == NetworkInfo.State.CONNECTED;
    }

    public static boolean isWIMAXNetworkAvailable(Context context) {
        logger.debug("isWIMAXNetworkAvailable");
        NetworkInfo info = getConnectivityManager(context).getNetworkInfo(ConnectivityManager.TYPE_WIMAX);
        return info != null && info.getState() == NetworkInfo.State.CONNECTED;
    }

    public static boolean isMobileNetworkAvailable(Context context) {
        logger.debug("isMobileNetworkAvailable");
        NetworkInfo info = getConnectivityManager(context).getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return info != null && info.getState() == NetworkInfo.State.CONNECTED;
    }

    public static boolean isInternetStreamingAvailable(Context context) {
        logger.debug("isInternetStreamingAvailable");
        return isWIMAXNetworkAvailable(context) || isWIFINetworkAvailable(context);
    }

    public static boolean isInternetAvailable(Context context) {
        logger.debug("isInternetAvailable");
        return isMobileNetworkAvailable(context) || isInternetStreamingAvailable(context);
    }

    private static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
