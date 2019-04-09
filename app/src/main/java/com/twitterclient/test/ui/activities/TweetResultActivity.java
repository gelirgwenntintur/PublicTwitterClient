package com.twitterclient.test.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.twitterclient.test.R;
import com.twitterclient.test.mvp.models.Tweet;
import com.twitterclient.test.mvp.presenters.TweetResultPresenter;
import com.twitterclient.test.mvp.views.TweetResultsView;
import com.twitterclient.test.ui.adapters.TweetResultAdapter;
import com.twitterclient.test.utils.DialogHelper;
import com.twitterclient.test.utils.Logger;

import java.util.List;

import static com.twitterclient.test.ui.activities.SearchActivity.BUNDLE_PARAM_SEARCH_TEXT;

public class TweetResultActivity extends BaseActivity implements TweetResultsView {

    private static final Logger logger = Logger.getLogger(TweetResultActivity.class);

    @InjectPresenter
    TweetResultPresenter presenter;

    private TweetResultAdapter adapter;
    private ProgressBar progressBar;


    @ProvidePresenter
    TweetResultPresenter provideTweetResultPresenter(){
        Bundle params = getIntent().getExtras();
        String searchTextByUsername = null;
        if (params != null) {
            searchTextByUsername = params.getString(BUNDLE_PARAM_SEARCH_TEXT);
        }
        return new TweetResultPresenter(searchTextByUsername);
    }

    @Override
    protected void onCreate(
        @Nullable
            Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger.debug("onCreate");

        setContentView(R.layout.activity_tweet);

        progressBar = findViewById(R.id.progressBarTweet);

        RecyclerView recyclerViewTweets = findViewById(R.id.recyclerViewTweets);
        recyclerViewTweets.setHasFixedSize(true);
        LinearLayoutManager layoutManagerTweets = new LinearLayoutManager(this);
        recyclerViewTweets.setLayoutManager(layoutManagerTweets);
        adapter = new TweetResultAdapter();
        recyclerViewTweets.setAdapter(adapter);
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
    }

    @Override
    public void showError(int stringId) {
        logger.debug("showError");
        DialogHelper.showSingleButtonDialog(this, getString(stringId), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
    }

    @Override
    public void updateActionBarTitle(String title) {
        logger.debug("updateActionBarTitle title = " + title);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showProgress(boolean isShown) {
        progressBar.setVisibility(isShown ? View.VISIBLE : View.GONE);
    }
}

