package com.tutorial.mvp.masterdetail.login;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.tutorial.mvp.masterdetail.R;
import com.tutorial.mvp.masterdetail.constants.AppConstants;
import com.tutorial.mvp.masterdetail.constants.ResultCode;

/**
 * Created by ankush3003 on 07/04/18.
 */

public class LoginInteractorImpl implements ILoginInteractor {

    private ILoginFinishedListener iLoginFinishedListener;

    @Override
    public void login(String username, String password, ILoginFinishedListener iLoginFinishedListener) {
        this.iLoginFinishedListener = iLoginFinishedListener;

        int validationResult = validateCredentials(username, password);
        if(validationResult == ResultCode.VALID_CREDENTIALS) {
            // Mock login
            new LoginTask().execute(new String[]{username, password});
        } else {
            iLoginFinishedListener.onError(validationResult);
        }
    }

    private int validateCredentials(String username, String password) {
        int result = ResultCode.VALID_CREDENTIALS;

        // Check for a valid username, if the user entered one.
        if (TextUtils.isEmpty(username)) {
            result = ResultCode.EMPTY_USERNAME;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            result = ResultCode.EMPTY_PASSWORD;
        }

        return result;
    }

    private class LoginTask extends AsyncTask<String[], Void, Boolean> {
        @Override
        protected Boolean doInBackground(String[]... strings) {
            // Dummy wait
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String[] enteredCredentials = strings[0];

            if (enteredCredentials[0].equals(AppConstants.DUMMY_CREDENTIALS[0]) &&
                    enteredCredentials[1].equals(AppConstants.DUMMY_CREDENTIALS[1])) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(success) {
                iLoginFinishedListener.onSuccess();
            } else {
                iLoginFinishedListener.onError(ResultCode.INVALID_CREDENTIALS);
            }
        }
    }
}
