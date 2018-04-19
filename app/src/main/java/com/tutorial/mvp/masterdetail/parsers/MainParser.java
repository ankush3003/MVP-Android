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
 * Created by rsgulati on 1/9/2018.
 */

public class MainParser {
    public static LinkedHashMap<CardData, ArrayList<CardData>> masterDataset = new LinkedHashMap<>();

    // returns result boolean
    public boolean generateMasterHashmap (Context ctx, boolean isFileDownloadedFromLink) {
        try {
            String jsonString = "";
            if(isFileDownloadedFromLink) {
                // read from Downloads Dir
                jsonString = loadJSONFromFile(ctx);
            } else {
                // read from assets
                jsonString = loadJSONFromAsset(ctx);
            }

            if (jsonString == null || TextUtils.isEmpty(jsonString)) {
                // file not found or IOException etc
                return false;
            }

            JSONObject source = new JSONObject(jsonString);
            JSONArray mainArray = source.getJSONArray(AppConstants.MAIN_ARRAY_KEY);
            for(int mainCatCounter = 0; mainCatCounter < mainArray.length(); mainCatCounter++) {
                // Master Category
                JSONObject masterObj = mainArray.getJSONObject(mainCatCounter);
                String masterCatName = masterObj.optString(AppConstants.MASTER_CATEGORY_NAME_KEY, "").toUpperCase();
                String masterCatIconName = masterObj.optString(AppConstants.MASTER_CATEGORY_ICON_LINK_KEY, "");

                //fetch sub categories under this master
                JSONArray detailArray = masterObj.getJSONArray(AppConstants.DETAIL_ARRAY_KEY);
                ArrayList<CardData> detailList = new ArrayList<>();
                for (int detailCatCounter = 0; detailCatCounter < detailArray.length(); detailCatCounter++) {
                    JSONObject detailObj = detailArray.getJSONObject(detailCatCounter);

                    String subCatName = detailObj.optString(AppConstants.SUB_CATEGORY_NAME_KEY, "");
                    String subCatIconName = detailObj.optString(AppConstants.SUB_CATEGORY_ICON_LINK_KEY, "");
                    String subCatHyperLink = detailObj.optString(AppConstants.SUB_CATEGORY_HYPERLINK_KEY, "");
                    String subCatHyperLinkType = detailObj.optString(AppConstants.SUB_CATEGORY_HYPERLINK_TYPE_KEY, "");

                    CardData detailCardData = new CardData(2, subCatName, subCatIconName, 0, subCatHyperLink, subCatHyperLinkType);
                    detailList.add(detailCardData);
                }
                CardData masterCardData = new CardData(1, masterCatName, masterCatIconName, detailList.size(), "", "");
                masterDataset.put(masterCardData, detailList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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

    private String loadJSONFromFile(Context context) {
        String jString = null;
        try {
            File masterFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), AppConstants.MASTER_DATA_FILENAME);
            FileInputStream stream = new FileInputStream(masterFile);
            try {
                FileChannel fc = stream.getChannel();
                MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            /* Instead of using default, pass in a decoder. */
                jString = Charset.defaultCharset().decode(bb).toString();
            }
            finally {
                stream.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jString;
    }
}
