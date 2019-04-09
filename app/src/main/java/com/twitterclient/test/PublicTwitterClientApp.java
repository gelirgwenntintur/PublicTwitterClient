package com.twitterclient.test;

import android.app.Application;

import com.twitterclient.test.di.AppComponent;
import com.twitterclient.test.di.DaggerAppComponent;
import com.twitterclient.test.di.modules.ContextModule;

public class PublicTwitterClientApp extends Application {

    private static PublicTwitterClientApp sApplication;
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

       sAppComponent = DaggerAppComponent.builder().contextModule(new ContextModule(this)).build();
    }

    public static PublicTwitterClientApp get() {
        return sApplication;
    }

    public static Application getContext() {
        return sApplication;
    }

    public static AppComponent getsAppComponent() {
        return sAppComponent;
    }
}
