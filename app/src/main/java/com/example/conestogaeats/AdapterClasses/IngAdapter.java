package com.example.conestogaeats.AdapterClasses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.conestogaeats.Models.Ingredient;
import com.example.conestogaeats.R;

import java.util.ArrayList;

public class IngAdapter extends RecyclerView.Adapter<IngViewHolder> {
    Context context;
    ArrayList<Ingredient> ings;

    public IngAdapter(Context context, ArrayList<Ingredient> ings) {
        this.context = context;
        this.ings = ings;
    }

    @NonNull
    @Override
    public IngViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new IngViewHolder(LayoutInflater.from(context).inflate(R.layout.ingre_itm_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngViewHolder ingViewHolder, int i) {
        Ingredient ingredient=ings.get(i);
        ingViewHolder.tvIng.setText(ingredient.getIngredient());
    }

    @Override
    public int getItemCount() {
        return ings.size();
    }
}
