package com.twitterclient.test.mvp.search;

import com.twitterclient.test.R;
import com.twitterclient.test.mvp.base.BasePresenter;
import com.twitterclient.test.utils.Logger;
import com.twitterclient.test.utils.NetworkUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchTweetPresenter extends BasePresenter<SearchTweetContract.View>
    implements SearchTweetContract.Presenter {

    private static final Logger logger = Logger.getLogger(SearchTweetPresenter.class);

    @Override
    public void search(String username) {
        logger.debug("search by username = " + username);

        checkViewAttached();

        if (!NetworkUtils.isInternetAvailable(getContext())) {
            getView().showError(R.string.error_title, R.string.error_internet_required);
        }
        else if (isUsernameValid(username)) {
            String searchText = username.contains("@") ? username : "@" + username;
            getView().showTweets(searchText);
        }
        else {
            getView().showError(R.string.error_title_invalid_username, R.string.error_title_invalid_username_description);
        }
    }

    private boolean isUsernameValid(String username) {
        logger.debug("isUsernameValid username = " + username);

        String patternString = "^@?(\\w){1,15}$";
        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
}
