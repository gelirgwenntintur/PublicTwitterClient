package com.twitterclient.test.api.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("search_metadata")
    public SearchMetadata searchMetadata;

    @SerializedName("statuses")
    public List<Tweet> tweetList;

    @Override
    public String toString() {
        return "SearchResponse{" +
                "searchMetadata=" + searchMetadata +
                ", tweetList=" + (tweetList != null ? tweetList.size() : 0) +
                '}';
    }

    public class SearchMetadata {
        @SerializedName("count")
        public String count;

        @SerializedName("max_id")
        public long maxId;

        @Override
        public String toString() {
            return "SearchMetadata{" +
                    "count='" + count + '\'' +
                    ", maxId=" + maxId +
                    '}';
        }
    }
}
