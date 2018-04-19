package com.tutorial.mvp.masterdetail.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.tutorial.mvp.masterdetail.constants.AppConstants;

/**
 * Created by rsgulati on 1/6/2018.
 */

public class CardData implements Parcelable{
    private int categoryLevel = AppConstants.CATEGORY_TYPE_LEVEL_ONE;
    private String categoryName;
    private String categoryIconString;
    private int childCount = 0;

    // for detail page only
    private String hyperlink;
    private String hyperlinkType;

    public CardData(int categoryLevel, String categoryName, String categoryIconString, int childCount, String hyperlink, String hyperlinkType) {
        this.categoryLevel = categoryLevel;
        this.categoryName = categoryName;
        this.categoryIconString = categoryIconString;
        this.childCount = childCount;
        this.hyperlink = hyperlink;
        this.hyperlinkType = hyperlinkType;
    }

    public int getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(int categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIconString() {
        return categoryIconString;
    }

    public void setCategoryIconString(String categoryIconString) {
        this.categoryIconString = categoryIconString;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public String getHyperlinkType() {
        return hyperlinkType;
    }

    public void setHyperlinkType(String hyperlinkType) {
        this.hyperlinkType = hyperlinkType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.categoryLevel);
        dest.writeString(this.categoryName);
        dest.writeString(this.categoryIconString);
        dest.writeInt(this.childCount);
        dest.writeString(this.hyperlink);
        dest.writeString(this.hyperlinkType);
    }

    protected CardData(Parcel in) {
        this.categoryLevel = in.readInt();
        this.categoryName = in.readString();
        this.categoryIconString = in.readString();
        this.childCount = in.readInt();
        this.hyperlink = in.readString();
        this.hyperlinkType = in.readString();
    }

    public static final Creator<CardData> CREATOR = new Creator<CardData>() {
        @Override
        public CardData createFromParcel(Parcel source) {
            return new CardData(source);
        }

        @Override
        public CardData[] newArray(int size) {
            return new CardData[size];
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;

        // object must be Test at this point
        CardData test = (CardData) obj;
        if (this.getCategoryName().equalsIgnoreCase(test.getCategoryName()))
            return true;
        else return false;
    }

    @Override
    public int hashCode()
    {
        return 10000*categoryLevel+childCount;
    }
}
