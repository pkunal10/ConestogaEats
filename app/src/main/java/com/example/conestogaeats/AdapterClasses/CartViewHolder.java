package com.example.conestogaeats.AdapterClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conestogaeats.R;

public class CartViewHolder extends RecyclerView.ViewHolder {
    ImageView ivCartProduct;
    TextView tvCartProductName,tvCartPrice,tvCartDeleteBtn,tvCartProductTotalPrice,btnCartDelete,dishId;
    EditText etCartQty;
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        ivCartProduct=itemView.findViewById(R.id.ivCartProductImg);
        tvCartProductName=itemView.findViewById(R.id.tvCartProductName);
        tvCartPrice=itemView.findViewById(R.id.tvCartPrice);
        tvCartDeleteBtn=itemView.findViewById(R.id.btnCartDelete);
        tvCartProductTotalPrice=itemView.findViewById(R.id.tvCartTotalPriceProduct);
        etCartQty=itemView.findViewById(R.id.etCartQty);
        btnCartDelete=itemView.findViewById(R.id.btnCartDelete);
        dishId=itemView.findViewById(R.id.dishId);
    }
}
