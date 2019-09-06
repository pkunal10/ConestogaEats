package com.example.conestogaeats.AdapterClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conestogaeats.R;

public class DishViewHolder extends RecyclerView.ViewHolder {
    ImageView mDishImg;
    TextView mDishName,mDishPrice;

    public DishViewHolder(@NonNull View itemView) {
        super(itemView);

        mDishImg=itemView.findViewById(R.id.imgDish);
        mDishName=itemView.findViewById(R.id.txtDishName);
        mDishPrice=itemView.findViewById(R.id.txtDishPrice);
    }
}
