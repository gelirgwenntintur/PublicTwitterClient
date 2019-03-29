package com.twitterclient.test.mvp.tweet;

import android.util.Log;

import com.twitterclient.test.R;
import com.twitterclient.test.api.TwitterApiClient;
import com.twitterclient.test.api.TwitterApiInterface;
import com.twitterclient.test.api.data.SearchResponse;
import com.twitterclient.test.api.data.Tweet;
import com.twitterclient.test.mvp.base.BasePresenter;
import com.twitterclient.test.utils.Logger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TweetPresenter extends BasePresenter<TweetResultsContract.View>
    implements TweetResultsContract.Presenter {

    private static final Logger logger = Logger.getLogger(TweetPresenter.class);
    private long maxId;

    @Override
    public void search(String username) {
        logger.debug("search username = " + username);

        checkViewAttached();

        getView().updateActionBarTitle(username);
        TwitterApiInterface apiInterface = TwitterApiClient.createServiceWithAuth(TwitterApiInterface.class);
        Call call = apiInterface.doSearch(username);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                handleSearchResponse(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                handleFailure(call, t);
            }
        });
    }

    private void handleFailure(Call call, Throwable t) {
        logger.error(t.getMessage(), t);
        call.cancel();
        getView().showError(R.string.error_getting_data);
    }

    private void handleSearchResponse(Response response) {
        logger.debug("handleSearchResponse code=" + response.code());

        if (response.isSuccessful() && response.body() != null) {
            SearchResponse resource = (SearchResponse) response.body();
            maxId = getMaxId(resource.tweetList);
            getView().showTweets(resource.tweetList);
            logger.debug("handleSearchResponse resource=" + resource.toString() + ", maxId=" + maxId + ", tweetsSize=" + resource.tweetList.size());

            if (resource.tweetList.isEmpty()) {
                getView().showError(R.string.error_no_data);
            }
        }
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
