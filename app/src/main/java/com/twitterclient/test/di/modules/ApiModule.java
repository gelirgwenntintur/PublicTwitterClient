package com.twitterclient.test.di.modules;

import com.twitterclient.test.api.TwitterApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = RetrofitModule.class)
public class ApiModule {

    @Provides
    @Singleton
    public TwitterApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(TwitterApi.class);
    }
}
