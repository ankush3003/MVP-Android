package com.tutorial.mvp.masterdetail.login;

/**
 * Created by ankush3003 on 07/04/18.
 */

public interface ILoginInteractor {

    interface ILoginFinishedListener{
        void onSuccess();
        void onError(int errorCode);
    }

    void login(String username, String password, ILoginFinishedListener iLoginFinishedListener);
}
