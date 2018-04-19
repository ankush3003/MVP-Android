package com.tutorial.mvp.masterdetail.login;

/**
 * Created by ankush3003 on 07/04/18.
 */

public interface ILoginView {

    void showLoading();
    void hideLoading();
    void setPasswordError(int stringID);
    void setEmailError(int stringID);
    void loginSuccess();
    void loginFailed(int errorCode);
}
