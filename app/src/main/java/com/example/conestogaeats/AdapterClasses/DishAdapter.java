package com.example.conestogaeats.AdapterClasses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conestogaeats.AlertDialogs.DishDetailsBottomSheet;
import com.example.conestogaeats.Models.Dish;
import com.example.conestogaeats.R;

import java.util.ArrayList;

public class DishAdapter extends RecyclerView.Adapter<DishViewHolder> {

    Context context;
    ArrayList<Dish> dishList;

    public DishAdapter(Context context, ArrayList<Dish> dishList) {
        this.context = context;
        this.dishList = dishList;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DishViewHolder(LayoutInflater.from(context).inflate(R.layout.dish_item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder dishViewHolder, int i) {
        final Dish dish = dishList.get(i);

        String prName=dish.getName().substring(0,3);
        prName=prName.substring(0,1).toLowerCase()+prName.substring(1,3);

        String img="com.example.conestogaeats:drawable/"+prName;
        int res=context.getResources().getIdentifier(img,null,null);

        dishViewHolder.mDishImg.setImageResource(res);

        dishViewHolder.mDishName.setText(dish.getName());
        dishViewHolder.mDishPrice.setText("$" + dish.getPrice());

        dishViewHolder.mDishImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DishDetailsBottomSheet dishDetailsBottomSheet = new DishDetailsBottomSheet();
                dishDetailsBottomSheet.setCurrentDish(dish);
                dishDetailsBottomSheet.show(((AppCompatActivity) context).getSupportFragmentManager(), "123");
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }
}
