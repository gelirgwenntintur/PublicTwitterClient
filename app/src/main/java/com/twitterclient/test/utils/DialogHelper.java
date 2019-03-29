package com.twitterclient.test.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

public class DialogHelper {
    private static final Logger logger = Logger.getLogger(DialogHelper.class);

    public static void showSingleButtonDialog(Context context, String message, String title, OnClickListener listener) {
        logger.debug("showSingleButtonDialog message = " + message + ", title = " + title);
        AlertDialog.Builder builder = getAlertDialogBuilder(context);
        builder.setMessage(message).setPositiveButton(android.R.string.ok, listener);
        if (title != null && title.isEmpty()) {
            builder.setTitle(title);
        }
        showDialog(builder);
    }

    public static void showSingleButtonDialog(Context context, String message) {
        logger.debug("showSingleButtonDialog message = " + message);
        showSingleButtonDialog(context, message, null);
    }

    public static void showSingleButtonDialog(Context context, String message, OnClickListener onClickListener) {
        logger.debug("showSingleButtonDialog message = " + message);
        showSingleButtonDialog(context, message, "", onClickListener);
    }

    public static void showDoubleButtonDialog(Context context, String message, int positiveButtonResId, int negativeButtonResId, OnClickListener negativeListener) {
        logger.debug("showDoubleButtonDialog message = " + message);
        showDoubleButtonDialog(context, null, message, positiveButtonResId, negativeButtonResId, negativeListener);
    }

    public static void showDoubleButtonDialog(Context context, String title, String message, int positiveButtonResId, int negativeButtonResId, OnClickListener clickListener) {
        logger.debug("showDoubleButtonDialog title = " + title + ", message = " + message);
        AlertDialog.Builder builder = getAlertDialogBuilder(context);
        builder.setMessage(message).setTitle(title).setNegativeButton(negativeButtonResId, clickListener).setPositiveButton(positiveButtonResId, clickListener);
        showDialog(builder);
    }

    private static void showDialog(AlertDialog.Builder builder) {
        builder.create();
        builder.setCancelable(false);
        builder.show();
    }

    private static AlertDialog.Builder getAlertDialogBuilder(Context context) {
        return new AlertDialog.Builder(context);
    }
}
