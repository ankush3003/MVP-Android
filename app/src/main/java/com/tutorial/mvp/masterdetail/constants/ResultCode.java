package com.tutorial.mvp.masterdetail.constants;

/**
 * Created by ankush3003 on 08/04/18.
 */

public class ResultCode {

    // Error codes for Login activity
    public static final int EMPTY_USERNAME = 1001;
    public static final int EMPTY_PASSWORD = 1002;
    public static final int INVALID_PASSWORD_CRITERIA = 1003;
    public static final int INVALID_CREDENTIALS = 1004;
    public static final int NETWORK_UNAVAILABLE = 1005;
    public static final int UNKNOWN_ERROR = 1006;
    public static final int VALID_CREDENTIALS = 1007;

    // Loading finished codes
    public static final int END_LOADING_WITH_SUCCESS = 1008;
    public static final int END_LOADING_WITH_FAILURE = 1009;
}
