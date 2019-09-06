package com.example.conestogaeats.AdapterClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conestogaeats.R;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    ImageView mResImage;
    TextView mTvName,mTvRatings,mTvDistance;
    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);

        mResImage=itemView.findViewById(R.id.rvResImage);
        mTvName=itemView.findViewById(R.id.rvResName);
        mTvRatings=itemView.findViewById(R.id.rvResRatings);
        mTvDistance=itemView.findViewById(R.id.rvResDistance);
    }
}
