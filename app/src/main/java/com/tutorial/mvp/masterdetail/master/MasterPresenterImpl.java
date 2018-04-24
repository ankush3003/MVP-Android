package com.tutorial.mvp.masterdetail.master;

import com.tutorial.mvp.masterdetail.models.CardData;

import java.util.ArrayList;

/**
 * Created by ankush3003 on 24/04/18.
 */

public class MasterPresenterImpl implements IMasterPresenter, IMasterInteractor.IDataParsedListener {

    IMasterView iMasterView;
    IMasterInteractor iMasterInteractor;

    public  MasterPresenterImpl(IMasterView iMasterView, IMasterInteractor iMasterInteractor) {
        this.iMasterView = iMasterView;
        this.iMasterInteractor = iMasterInteractor;
    }

    @Override
    public void parseData(String json) {
        if(iMasterView != null) {
            iMasterInteractor.parseData(json, this);
        }
    }

    @Override
    public void onSuccess(ArrayList<CardData> cardData) {
        if(iMasterView != null) {
            iMasterView.showData(cardData);
        }
    }

    @Override
    public void onError() {
        if(iMasterView != null) {
            iMasterView.showParseError();
        }
    }
}
