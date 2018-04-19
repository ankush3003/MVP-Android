package com.tutorial.mvp.masterdetail.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tutorial.mvp.masterdetail.R;
import com.tutorial.mvp.masterdetail.constants.AppConstants;
import com.tutorial.mvp.masterdetail.interfaces.RecyclerViewClickListener;
import com.tutorial.mvp.masterdetail.models.CardData;

import java.util.List;

/**
 * Created by rsgulati on 1/6/2018.
 */
public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.MyViewHolder> {

    private Context mContext;
    private List<CardData> cardDataList;
    private int fragmentType = AppConstants.FRAGMENT_TYPE_MASTER_LIST;
    private RecyclerViewClickListener recyclerViewClickListener;

    public MainCategoryAdapter(Context mContext, RecyclerViewClickListener recyclerViewClickListener, List<CardData> cardDataList, int fragmentType) {
        this.mContext = mContext;
        this.recyclerViewClickListener = recyclerViewClickListener;
        this.cardDataList = cardDataList;
        this.fragmentType = fragmentType;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        CardData cardData = cardDataList.get(position);
        holder.cardTitle.setText(cardData.getCategoryName());

        if (fragmentType == AppConstants.FRAGMENT_TYPE_MASTER_LIST) {
            String text = cardData.getChildCount() + " " + mContext.getString(R.string.subchild_count_postfix);
            holder.subcardCount.setText(text);

            // drawable image as specified in json
            /*int categoryIconID = AppConstants.getCategoryIconID(mContext, cardData.getCategoryIconString());
            if(categoryIconID != 0) {
                Glide.with(mContext).load(categoryIconID).into(holder.thumbnail);
            }*/
            holder.cardTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            holder.subcardCount.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        } else {
            holder.subcardCount.setText(cardData.getHyperlinkType());
            holder.cardTitle.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.subcardCount.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            // youtube thumbnail or as specified in json
            if(cardData.getHyperlinkType().contains("Youtube")) {
                String thumbLink = AppConstants.getYoutubeThumbnailLink(cardData.getHyperlink());
                Glide
                        .with(mContext)
                        .load(thumbLink)
                        .centerCrop()
                        .crossFade()
                        .placeholder(R.drawable.placeholder)
                        .into(holder.thumbnail);
            } else {
                /*int categoryIconID = AppConstants.getCategoryIconID(mContext, cardData.getCategoryIconString());
                if(categoryIconID != 0) {
                    Glide.with(mContext).load(categoryIconID).into(holder.thumbnail);
                }*/
            }
        }
        holder.itemView.setTag(cardData);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewClickListener.onViewClicked(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cardTitle, subcardCount;
        ImageView thumbnail;

        MyViewHolder(View view) {
            super(view);
            cardTitle = (TextView) view.findViewById(R.id.cardTitle);
            subcardCount = (TextView) view.findViewById(R.id.subcardCount);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }
}