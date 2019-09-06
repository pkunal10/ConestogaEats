package com.example.conestogaeats.AdapterClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conestogaeats.R;

public class OrderItemViewHolder extends RecyclerView.ViewHolder {
    TextView txtDishName,txtDishPrice,txtQty;
    ImageView imgDish;
    public OrderItemViewHolder(@NonNull View itemView) {
        super(itemView);
        txtDishName=itemView.findViewById(R.id.txtDishName);
        txtDishPrice=itemView.findViewById(R.id.txtDishPrice);
        txtQty=itemView.findViewById(R.id.txtQty);
        imgDish=itemView.findViewById(R.id.imgDish);
    }
}
