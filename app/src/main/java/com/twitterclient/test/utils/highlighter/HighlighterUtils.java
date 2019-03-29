package com.twitterclient.test.utils.highlighter;

import android.widget.TextView;

import com.twitterclient.test.utils.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HighlighterUtils {

    private static final Logger logger = Logger.getLogger(HighlighterUtils.class);

    public static void highlightText(TextView textView) {
        logger.debug("highlightText");

        Highlighter.TransformFilter filter = new Highlighter.TransformFilter() {
            public final String transformUrl(final Matcher match, String url) {
                return match.group();
            }
        };
        Pattern mentionPattern = Pattern.compile("@([A-Za-z0-9_-]+)");
        String mentionScheme = "http://www.twitter.com/";
        Highlighter.addLinks(textView, mentionPattern, mentionScheme, null, filter, null);

        Pattern hashTagPattern = Pattern.compile("#([A-Za-z0-9_-]+)");
        String hashTagScheme = "http://www.twitter.com/search/";
        Highlighter.addLinks(textView, hashTagPattern, hashTagScheme, null, filter, null);
    }
}
