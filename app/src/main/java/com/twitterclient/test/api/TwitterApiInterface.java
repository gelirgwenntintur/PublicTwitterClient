package com.twitterclient.test.api;

import com.twitterclient.test.api.data.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TwitterApiInterface {

    @GET("search/tweets.json?")
    Call<SearchResponse> doSearch(
        @Query("q")
            String text);
}
