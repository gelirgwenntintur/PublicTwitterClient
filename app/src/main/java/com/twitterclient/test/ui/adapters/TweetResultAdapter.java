
package com.twitterclient.test.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twitterclient.test.R;
import com.twitterclient.test.mvp.models.Tweet;
import com.twitterclient.test.mvp.models.User;
import com.twitterclient.test.utils.Logger;
import com.twitterclient.test.utils.highlighter.HighlighterUtils;

import java.util.ArrayList;
import java.util.List;

public class TweetResultAdapter extends RecyclerView.Adapter<TweetResultAdapter.ViewHolder> {

    private static final Logger logger = Logger.getLogger(TweetResultAdapter.class);

    private List<Tweet> tweets;
    public static final int TARGET_PHOTO_SIZE = 120;

    public TweetResultAdapter() {
        tweets = new ArrayList<>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewUserPhoto;
        private TextView textViewTweetInfo;
        private TextView textViewUsername;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewUserPhoto = itemView.findViewById(R.id.imageViewTweetPhoto);
            textViewTweetInfo = itemView.findViewById(R.id.textViewTweetText);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_tweet_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Tweet tweet = tweets.get(position);
        holder.textViewTweetInfo.setText(tweet.getText());

        HighlighterUtils.highlightText(holder.textViewTweetInfo);

        User user = tweet.getUser();
        if (user != null) {
            holder.textViewUsername.setText(user.getScreenName());
            Picasso.get().load(user.getProfileImageUrl()).resize(TARGET_PHOTO_SIZE, TARGET_PHOTO_SIZE).centerCrop().into(holder.imageViewUserPhoto);
        }
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void setTweets(List<Tweet> tweets) {
        if (tweets == null || tweets.isEmpty()) {
            return;
        }
        this.tweets.addAll(tweets);
    }

    public void clear() {
        logger.debug("clear");
        tweets.clear();
    }
}
