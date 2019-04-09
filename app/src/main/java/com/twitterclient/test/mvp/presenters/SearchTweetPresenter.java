package com.twitterclient.test.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.twitterclient.test.PublicTwitterClientApp;
import com.twitterclient.test.R;
import com.twitterclient.test.mvp.views.SearchView;
import com.twitterclient.test.utils.Logger;
import com.twitterclient.test.utils.NetworkUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@InjectViewState
public class SearchTweetPresenter extends BasePresenter<SearchView> {

    private static final Logger logger = Logger.getLogger(SearchTweetPresenter.class);

    public void search(String username) {
        logger.debug("search by username = " + username);

        if (!NetworkUtils.isInternetAvailable(PublicTwitterClientApp.getsAppComponent().getContext())) {
            getViewState().showError(R.string.error_title, R.string.error_internet_required);
        }
        else if (isUsernameValid(username)) {
            String searchText = username.contains("@") ? username : "@" + username;
            getViewState().showTweets(searchText);
        }
        else {
            getViewState().showError(R.string.error_title_invalid_username, R.string.error_title_invalid_username_description);
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
