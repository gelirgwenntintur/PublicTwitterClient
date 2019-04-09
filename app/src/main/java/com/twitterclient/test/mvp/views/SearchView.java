package com.twitterclient.test.mvp.views;

import com.arellomobile.mvp.MvpView;

public interface SearchView extends MvpView {
    void showTweets(String searchText);

    void showError(int titleStringId, int messageStringId);
}
