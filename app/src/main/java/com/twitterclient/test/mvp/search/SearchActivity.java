package com.twitterclient.test.mvp.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.twitterclient.test.R;
import com.twitterclient.test.mvp.BaseActivity;
import com.twitterclient.test.Constants;
import com.twitterclient.test.mvp.tweet.TweetResultActivity;
import com.twitterclient.test.utils.DialogHelper;
import com.twitterclient.test.utils.Logger;

public class SearchActivity extends BaseActivity implements SearchTweetContract.View {

    private static final Logger logger = Logger.getLogger(SearchActivity.class);

    private SearchTweetPresenter presenter;
    private Button buttonSearch;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger.debug("onCreate");

        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buttonSearch = findViewById(R.id.buttonSearch);
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.debug("handle click on search button");
                hideSoftKeyboard(v, SearchActivity.this);
                presenter.search(editTextSearch.getText().toString());
            }
        });

        presenter = new SearchTweetPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        logger.debug("onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        logger.debug("onOptionsItemSelected");
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            showExitDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        logger.debug("onStop");
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void onBackPressed() {
        logger.debug("onBackPressed");
        showExitDialog();
    }

    @Override
    public void showTweets(String username) {
        logger.debug("open tweet results by username = " + username);
        Intent intent = new Intent(this, TweetResultActivity.class);
        intent.putExtra(Constants.BUNDLE_PARAM_SEARCH_TEXT, username);
        startActivity(intent);
    }

    @Override
    public void showError(int titleStringId, int messageStringId) {
        logger.debug("showError");
        DialogHelper.showSingleButtonDialog(this, getString(messageStringId), getString(titleStringId), null);
    }
}
