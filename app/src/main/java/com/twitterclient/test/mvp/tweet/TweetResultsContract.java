package com.twitterclient.test.mvp.tweet;

import com.twitterclient.test.api.data.Tweet;
import com.twitterclient.test.mvp.base.IBasePresenter;
import com.twitterclient.test.mvp.base.IBaseView;

import java.util.List;

public interface TweetResultsContract {
    interface View extends IBaseView {
        void showTweets(List<Tweet> tweets);

        void showError(int stringId);

        void updateActionBarTitle(String title);
    }

    interface Presenter extends IBasePresenter<TweetResultsContract.View> {
        void search(String searchText);
    }
}
