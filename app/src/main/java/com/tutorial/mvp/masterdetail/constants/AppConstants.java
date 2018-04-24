package com.tutorial.mvp.masterdetail.constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ankush3003 on 1/6/2018.
 *
 * App wide constants for json parsing and dummy credentials for now.
 */

public class AppConstants {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    public static final String[] DUMMY_CREDENTIALS = new String[]{
            "android", "mvp"
    };

    // Json parsing constants
    public static final String MASTER_DATA_FILENAME = "sample_data.json";
    public static final String MAIN_ARRAY_KEY = "main";
    public static final String CARD_TITLE_KEY = "card_title";
    public static final String CARD_HYPERLINK_KEY = "hyperlink";
    public static final String CARD_DESCRIPTION_KEY = "card_description";

    /**
     * Extract youtube video thumbnail from video link
     *
     * @param ytVideoLink Target youtube video link
     * @return video thumbnail link
     */
    public static String getYoutubeThumbnailLink(String ytVideoLink) {
        return "http://img.youtube.com/vi/" + extractYoutubeID(ytVideoLink) + "/0.jpg";
    }

    /**
     * Extracts youtube video ID from link.
     *
     * @param ytUrl
     * @return video ID
     */
    private static String extractYoutubeID(String ytUrl) {
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(ytUrl);

        if(matcher.find()){
            return matcher.group();
        } else {
            return null;
        }
    }
}
