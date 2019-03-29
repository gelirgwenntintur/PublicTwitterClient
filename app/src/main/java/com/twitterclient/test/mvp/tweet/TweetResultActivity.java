package com.twitterclient.test.mvp.tweet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.twitterclient.test.R;
import com.twitterclient.test.api.data.Tweet;
import com.twitterclient.test.mvp.BaseActivity;
import com.twitterclient.test.Constants;
import com.twitterclient.test.utils.DialogHelper;
import com.twitterclient.test.utils.Logger;

import java.util.List;

public class TweetResultActivity extends BaseActivity implements TweetResultsContract.View {

    private static final Logger logger = Logger.getLogger(TweetResultActivity.class);

    private TweetPresenter presenter;
    private TweetResultAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(
        @Nullable
            Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger.debug("onCreate");

        setContentView(R.layout.activity_tweet);

        progressBar = findViewById(R.id.progressBarTweet);
        progressBar.setVisibility(View.VISIBLE);

        RecyclerView recyclerViewTweets = findViewById(R.id.recyclerViewTweets);
        recyclerViewTweets.setHasFixedSize(true);
        LinearLayoutManager layoutManagerTweets = new LinearLayoutManager(this);
        recyclerViewTweets.setLayoutManager(layoutManagerTweets);
        adapter = new TweetResultAdapter();
        recyclerViewTweets.setAdapter(adapter);

        presenter = new TweetPresenter();
        presenter.attachView(this);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            String searchTextByUsername = params.getString(Constants.BUNDLE_PARAM_SEARCH_TEXT);
            presenter.search(searchTextByUsername);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        logger.debug("onStop");
        presenter.detachView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        logger.debug("onOptionsItemSelected");
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void showTweets(List<Tweet> tweets) {
        logger.debug("showTweets");
        adapter.clear();
        adapter.setTweets(tweets);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(int stringId) {
        logger.debug("showError");
        DialogHelper.showSingleButtonDialog(this, getString(stringId));
    }

    @Override
    public void updateActionBarTitle(String title) {
        logger.debug("updateActionBarTitle title = " + title);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
