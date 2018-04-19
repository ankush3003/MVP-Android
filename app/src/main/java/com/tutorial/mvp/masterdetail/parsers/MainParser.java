package com.tutorial.mvp.masterdetail.parsers;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.tutorial.mvp.masterdetail.constants.AppConstants;
import com.tutorial.mvp.masterdetail.models.CardData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by ankush3003 on 1/9/2018.
 */

public class MainParser {

    // returns result boolean
    public ArrayList<CardData> getData (Context ctx) {
        ArrayList<CardData> result = new ArrayList<>();

        try {
            String jsonString = loadJSONFromAsset(ctx);

            if (jsonString == null || TextUtils.isEmpty(jsonString)) {
                // file not found or IOException etc
                return null;
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
            return null;
        }
        return result;
    }

    private String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = ((Activity)context).getAssets().open(AppConstants.MASTER_DATA_FILENAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
