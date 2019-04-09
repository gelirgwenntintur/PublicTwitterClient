package com.twitterclient.test.mvp;

import com.twitterclient.test.api.TwitterApi;
import com.twitterclient.test.mvp.models.gson.SearchResponse;

import rx.Observable;

public class TwitterService {
    private TwitterApi twitterApi;

    public TwitterService(TwitterApi twitterApi) {
        this.twitterApi = twitterApi;
    }

    public Observable<SearchResponse> getSearchResults(String username){
        return twitterApi.search(username);
    }
}
