package com.example.conestogaeats.AdapterClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.conestogaeats.R;

public class IngViewHolder extends RecyclerView.ViewHolder {
    TextView tvIng;
    public IngViewHolder(@NonNull View itemView) {
        super(itemView);
        tvIng=itemView.findViewById(R.id.tvIngName);
    }
}
