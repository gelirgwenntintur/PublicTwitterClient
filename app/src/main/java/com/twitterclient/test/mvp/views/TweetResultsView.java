package com.twitterclient.test.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.twitterclient.test.mvp.models.Tweet;

import java.util.List;

public interface TweetResultsView extends MvpView {
    void showTweets(List<Tweet> tweets);

    void showError(int stringId);

    void updateActionBarTitle(String title);

    void showProgress(boolean isShown);
}
