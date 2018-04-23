package com.tutorial.mvp.masterdetail.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Created by ankush3003 on 1/6/2018.
 */
public class MasterActivityAdapter extends RecyclerView.Adapter<MasterActivityAdapter.MyViewHolder> {

    private Context mContext;
    private List<CardData> cardDataList;
    private RecyclerViewClickListener recyclerViewClickListener;

    public MasterActivityAdapter(Context mContext, RecyclerViewClickListener recyclerViewClickListener, List<CardData> cardDataList) {
        this.mContext = mContext;
        this.recyclerViewClickListener = recyclerViewClickListener;
        this.cardDataList = cardDataList;
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
        holder.cardTitle.setText(cardData.getCardTitle());
        holder.cardSubtitle.setText(cardData.getCardDescription());

        Glide
                .with(mContext)
                .load(AppConstants.getYoutubeThumbnailLink(cardData.getCardLink()))
                .fitCenter()
                .crossFade()
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

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
        TextView cardTitle, cardSubtitle;
        ImageView thumbnail;

        MyViewHolder(View view) {
            super(view);
            cardTitle = (TextView) view.findViewById(R.id.cardTitle);
            cardSubtitle = (TextView) view.findViewById(R.id.cardSubtitle);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }
}