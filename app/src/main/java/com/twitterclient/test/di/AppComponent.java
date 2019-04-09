package com.twitterclient.test.di;

import android.content.Context;

import com.twitterclient.test.api.TwitterApi;
import com.twitterclient.test.di.modules.ContextModule;
import com.twitterclient.test.di.modules.TwitterModule;
import com.twitterclient.test.mvp.TwitterService;
import com.twitterclient.test.mvp.presenters.SearchTweetPresenter;
import com.twitterclient.test.mvp.presenters.TweetResultPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, TwitterModule.class})
public interface AppComponent {
    Context getContext();

    TwitterApi getTwitterApi();

    TwitterService getAuthService();

    void inject(SearchTweetPresenter presenter);

    void inject(TweetResultPresenter presenter);
}
