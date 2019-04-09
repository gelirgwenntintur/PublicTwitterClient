package com.twitterclient.test.api;

import com.twitterclient.test.mvp.models.gson.SearchResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TwitterApi {

    @GET("search/tweets.json?")
    Observable<SearchResponse> search(@Query("q") String text);
}
