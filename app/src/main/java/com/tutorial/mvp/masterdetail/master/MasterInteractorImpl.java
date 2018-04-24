package com.tutorial.mvp.masterdetail.master;

import android.text.TextUtils;

import com.tutorial.mvp.masterdetail.constants.AppConstants;
import com.tutorial.mvp.masterdetail.models.CardData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ankush3003 on 25/04/18.
 */

public class MasterInteractorImpl implements IMasterInteractor {

    @Override
    public void parseData(String jsonString, IDataParsedListener dataParsedListener) {
        ArrayList<CardData> result = new ArrayList<>();

        try {
            if (jsonString == null || TextUtils.isEmpty(jsonString)) {
                // file not found or IOException etc
                dataParsedListener.onError();
            }

            JSONObject source = new JSONObject(jsonString);
            JSONArray mainArray = source.getJSONArray(AppConstants.MAIN_ARRAY_KEY);
            for(int counter = 0; counter < mainArray.length(); counter++) {
                JSONObject jsonObject = mainArray.getJSONObject(counter);

                CardData cardData = new CardData(
                        jsonObject.optString(AppConstants.CARD_TITLE_KEY, "")
                        , jsonObject.optString(AppConstants.CARD_HYPERLINK_KEY, "")
                        , jsonObject.optString(AppConstants.CARD_DESCRIPTION_KEY, ""));
                result.add(cardData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            dataParsedListener.onError();
        }
        dataParsedListener.onSuccess(result);
    }
}
