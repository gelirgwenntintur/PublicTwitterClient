package com.twitterclient.test.mvp.models;

import com.google.gson.annotations.SerializedName;

public class Tweet {
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("id")
    public String id;
    @SerializedName("text")
    public String text;
    @SerializedName("user")
    public User user;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tweet tweet = (Tweet) o;

        return id != null ? id.equals(tweet.id) : tweet.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Tweet {" + "createdAt='" + createdAt + '\'' + ", id='" + id + '\'' + ", text='" + text + '\'' + ", user=" + user + '}';
    }
}
