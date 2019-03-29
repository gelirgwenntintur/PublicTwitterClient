package com.twitterclient.test.mvp.search;

import com.twitterclient.test.mvp.base.IBasePresenter;
import com.twitterclient.test.mvp.base.IBaseView;

public interface SearchTweetContract {

    interface View extends IBaseView {
        void showTweets(String searchText);

        void showError(int titleStringId, int messageStringId);
    }

    interface Presenter extends IBasePresenter<View> {
        void search(String searchText);
    }
}
