package com.tutorial.mvp.masterdetail.login;

import com.tutorial.mvp.masterdetail.R;
import com.tutorial.mvp.masterdetail.constants.ResultCode;

/**
 * Created by ankush3003 on 07/04/18.
 */

public class LoginPresenterImpl implements ILoginPresenter, ILoginInteractor.ILoginFinishedListener {

    ILoginView loginView;
    ILoginInteractor loginInteractor;

    public LoginPresenterImpl(ILoginView loginView, ILoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void doLogin(String username, String password) {
        if(loginView != null) {
            loginView.showLoading();
            loginInteractor.login(username, password, this);
        }
    }

    @Override
    public void onSuccess() {
        if(loginView != null) {
            loginView.hideLoading();
            loginView.loginSuccess();
        }
    }

    @Override
    public void onError(int errorCode) {
        if(loginView != null) {
            loginView.hideLoading();

            switch (errorCode) {
                case ResultCode.EMPTY_USERNAME:
                    loginView.setEmailError(R.string.error_field_required);
                    break;

                case ResultCode.EMPTY_PASSWORD:
                    loginView.setPasswordError(R.string.error_field_required);
                    break;

                case ResultCode.INVALID_PASSWORD_CRITERIA:
                    loginView.setPasswordError(R.string.error_invalid_password);
                    break;

                case ResultCode.INVALID_CREDENTIALS:
                    loginView.setPasswordError(R.string.error_incorrect_password);
                    break;

            }
            loginView.loginFailed(errorCode);
        }
    }
}
