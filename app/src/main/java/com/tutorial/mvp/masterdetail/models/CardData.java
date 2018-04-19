package com.tutorial.mvp.masterdetail.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.tutorial.mvp.masterdetail.constants.AppConstants;

/**
 * Created by ankush3003 on 1/6/2018.
 */

public class CardData implements Parcelable{

    private String cardTitle;
    private String cardLink;
    private String cardDescription;

    public CardData(String cardTitle, String cardLink, String cardDescription) {
        this.cardTitle = cardTitle;
        this.cardLink = cardLink;
        this.cardDescription = cardDescription;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardLink() {
        return cardLink;
    }

    public void setCardLink(String cardLink) {
        this.cardLink = cardLink;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cardTitle);
        dest.writeString(cardLink);
        dest.writeString(cardDescription);
    }

    protected CardData(Parcel in) {
        this.cardTitle = in.readString();
        this.cardLink = in.readString();
        this.cardDescription = in.readString();
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
}
