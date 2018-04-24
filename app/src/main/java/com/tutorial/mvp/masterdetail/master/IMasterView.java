package com.tutorial.mvp.masterdetail.master;

import com.tutorial.mvp.masterdetail.models.CardData;

import java.util.ArrayList;

/**
 * Created by ankush3003 on 24/04/18.
 */

public interface IMasterView {
    void showData(ArrayList<CardData> data);
    void showParseError();
}
