package com.example.conestogaeats.AdapterClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.conestogaeats.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    TextView ohOdrDate,ohTotalItems,ohTotalBill;
    RecyclerView rvOHOI;
    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        ohOdrDate=itemView.findViewById(R.id.ohOdrDate);
        ohTotalItems=itemView.findViewById(R.id.ohNoOfItms);
        ohTotalBill=itemView.findViewById(R.id.ohTotalBill);
        rvOHOI=itemView.findViewById(R.id.rvOHOI);
    }
}
