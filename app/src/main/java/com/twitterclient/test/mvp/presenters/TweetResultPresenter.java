package com.twitterclient.test.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.twitterclient.test.PublicTwitterClientApp;
import com.twitterclient.test.R;
import com.twitterclient.test.mvp.TwitterService;
import com.twitterclient.test.mvp.models.gson.SearchResponse;
import com.twitterclient.test.mvp.models.Tweet;
import com.twitterclient.test.mvp.views.TweetResultsView;
import com.twitterclient.test.utils.Logger;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class TweetResultPresenter extends BasePresenter<TweetResultsView> {

    private static final Logger logger = Logger.getLogger(TweetResultPresenter.class);
    private long maxId;

    @Inject
    TwitterService twitterService;

    private String username;

    public TweetResultPresenter(String username) {
        this.username = username;
        PublicTwitterClientApp.getsAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        search(username);
    }

    public void search(String username) {
        logger.debug("search username = " + username);

        getViewState().updateActionBarTitle(username);
        getViewState().showProgress(true);

        Subscription subscription = twitterService.getSearchResults(username).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SearchResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                handleFailure(e);
            }

            @Override
            public void onNext(SearchResponse searchResponse) {
                handleSearchResponse(searchResponse);
            }
        });

        unsubscribeOnDestroy(subscription);
    }

    private void handleFailure(Throwable t) {
        logger.error(t.getMessage(), t);
        getViewState().showProgress(false);
        getViewState().showError(R.string.error_getting_data);
    }

    private void handleSearchResponse(SearchResponse resource) {
        logger.debug("handleSearchResponse");
        getViewState().showProgress(false);
        if (resource == null || resource.tweetList == null || resource.tweetList.isEmpty()) {
            getViewState().showError(R.string.error_no_data);
            return;
        }
        maxId = getMaxId(resource.tweetList);
        getViewState().showTweets(resource.tweetList);
        logger.debug("handleSearchResponse resource=" + resource.toString() + ", maxId=" + maxId + ", tweetsSize=" + resource.tweetList.size());
    }

    private long getMaxId(List<Tweet> tweetList) {
        logger.debug("getMaxId");
        long min = 0;
        if (tweetList != null && !tweetList.isEmpty()) {
            min = Long.parseLong(tweetList.get(0).getId());
            for (int i = 1; i < tweetList.size(); i++) {
                Tweet t = tweetList.get(i);
                if (Long.parseLong(t.getId()) < min) {
                    min = Long.parseLong(t.getId());
                }
            }
        }
        return min;
    }
}
