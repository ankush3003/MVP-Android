package com.tutorial.mvp.masterdetail.constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rsgulati on 1/6/2018.
 */

public class AppConstants {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    public static final String[] DUMMY_CREDENTIALS = new String[]{
            "android", "mvp"
    };

    public static final String MASTER_DATA_FILENAME = "master_data.json";
    public static final int CATEGORY_TYPE_LEVEL_ONE = 1001;

    public static final String FRAGMENT_TYPE_KEY = "fragment_type";
    public static final int FRAGMENT_TYPE_MASTER_LIST = 2001;

    public static final String SELECTED_MASTER_LIST_OBJECT_KEY = "selected_master_list_obj";

    // Json related
    public static final String MAIN_ARRAY_KEY = "main";
    public static final String DETAIL_ARRAY_KEY = "sub_categories";

    public static final String MASTER_CATEGORY_NAME_KEY = "category_name";
    public static final String MASTER_CATEGORY_ICON_LINK_KEY = "category_icon_link";

    public static final String SUB_CATEGORY_NAME_KEY = "sub_category_name";
    public static final String SUB_CATEGORY_ICON_LINK_KEY = "sub_category_icon_link";
    public static final String SUB_CATEGORY_HYPERLINK_KEY = "sub_category_hyperlink";
    public static final String SUB_CATEGORY_HYPERLINK_TYPE_KEY = "sub_category_hyperlink_type";

    public static final String MAIL_RECEPIENT = "explorertheapp@gmail.com";
    public static final String MAIL_SUBJECT = "Explore App Feedback/Suggestion";
    public static final String MAIL_TEXT = "Hi, \n\nGreat job in developing Explore App!!!.\nI believe that including following suggestions will make App even better.\n\n<Enter here>";

    public static String getYoutubeThumbnailLink(String ytVideoLink) {
        return "http://img.youtube.com/vi/" + extractYoutubeID(ytVideoLink) + "/0.jpg";
    }

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
