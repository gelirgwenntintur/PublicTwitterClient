package com.twitterclient.test.ui.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.twitterclient.test.PublicTwitterClientApp;
import com.twitterclient.test.R;
import com.twitterclient.test.utils.DialogHelper;
import com.twitterclient.test.utils.Logger;

public class BaseActivity extends MvpAppCompatActivity {

    private static final Logger logger = Logger.getLogger(BaseActivity.class);

    public void hideSoftKeyboard(View view, Activity activity) {
        logger.debug("hideSoftKeyboard");

        if (view != null) {
            Context context = PublicTwitterClientApp.get().getApplicationContext();
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                if (android.os.Build.VERSION.SDK_INT < 11) {
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                else {
                    if (activity.getCurrentFocus() != null) {
                        inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    view.clearFocus();
                }
                view.clearFocus();
            }
        }
    }

    public void showExitDialog() {
        logger.debug("showExitDialog");
        DialogHelper.showDoubleButtonDialog(this, getString(R.string.dialog_exit_message), R.string.dialog_exit_button_yes, R.string.dialog_exit_button_No, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int what) {
                if (what == Dialog.BUTTON_POSITIVE) {
                    finish();
                }
            }
        });
    }
}
