package com.twitterclient.test;

import android.app.Application;

public class PublicTwitterClientApp extends Application {

    private static PublicTwitterClientApp sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static PublicTwitterClientApp get() {
        return sApplication;
    }

    public static Application getContext() {
        return sApplication;
    }
}
