package com.tutorial.mvp.masterdetail.master;

import com.tutorial.mvp.masterdetail.models.CardData;

import java.util.ArrayList;

/**
 * Created by ankush3003 on 24/04/18.
 */

public interface IMasterInteractor {

    interface IDataParsedListener{
        void onSuccess(ArrayList<CardData> cardData);
        void onError();
    }

    void parseData(String jsonString, IDataParsedListener dataParsedListener);
}
