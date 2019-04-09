package com.twitterclient.test.di.modules;

import com.twitterclient.test.api.TwitterApi;
import com.twitterclient.test.mvp.TwitterService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class})
public class TwitterModule {
    @Provides
    @Singleton
    public TwitterService provideTwitterService(TwitterApi authApi) {
        return new TwitterService(authApi);
    }
}
