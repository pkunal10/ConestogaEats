package com.example.conestogaeats.AlertDialogs;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conestogaeats.AdapterClasses.IngAdapter;
import com.example.conestogaeats.Models.Dish;
import com.example.conestogaeats.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DishDetailsBottomSheet extends BottomSheetDialogFragment {

    ImageView mImgDish;
    TextView mDishName, mDishCal, mDishPrice;
    RecyclerView mRvIng;
    Dish dish;
    LinearLayoutManager linearLayoutManager;
    IngAdapter ingAdapter;
    TextView mTv;
    Button mBtnAddToCart;
    Set<String> productSet = new HashSet<>();
    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dish_details_bottomsheet_layout, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mImgDish = view.findViewById(R.id.imgViewDish);
        mDishName = view.findViewById(R.id.viewDishName);
        mDishCal = view.findViewById(R.id.viewDishCalories);
        mDishPrice = view.findViewById(R.id.viewDishPrice);
        mRvIng = view.findViewById(R.id.rvIng);
        mTv = view.findViewById(R.id.textView2);
        mBtnAddToCart = view.findViewById(R.id.btnAddToCart);
        sp = getActivity().getSharedPreferences("CE", Context.MODE_PRIVATE);

        mDishName.setText(dish.getName());
        mDishPrice.setText("$" + dish.getPrice());
        mDishCal.setText("Calories : " + dish.getCalories());
        String prName=dish.getName().substring(0,3);
        prName=prName.substring(0,1).toLowerCase()+prName.substring(1,3);

        String img="com.example.conestogaeats:drawable/"+prName;
        int res=getActivity().getResources().getIdentifier(img,null,null);

        mImgDish.setImageResource(res);


        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ingAdapter = new IngAdapter(getActivity(), dish.getIngredients());
        mRvIng.setLayoutManager(linearLayoutManager);
        mRvIng.setAdapter(ingAdapter);

        mTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);

                if (mRvIng.getVisibility() == View.GONE) {
                    mRvIng.setAnimation(animation);
                    mRvIng.setVisibility(View.VISIBLE);
                } else {
                    mRvIng.setVisibility(View.GONE);
                }
            }
        });


        mBtnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProductInCart(dish.getId());
            }
        });

    }

    public void setCurrentDish(Dish dish) {
        this.dish = dish;
    }

    private void AddProductInCart(String productId) {

        if (sp.contains("ProductSet")) {
            Set<String> pSet = new HashSet<>();
            pSet = sp.getStringSet("ProductSet", null);
            List<String> plist = new ArrayList<>(pSet);
            if (pSet.contains(productId)) {
                Toast.makeText(getActivity(), "Product Already Added In Cart", Toast.LENGTH_LONG).show();
            } else {

                productSet.add(productId);
                for (int i = 0; i < plist.size(); i++) {
                    productSet.add(plist.get(i));
                }
                SharedPreferences.Editor editor = sp.edit();
                // productSet.add(productId);
                editor.putStringSet("ProductSet", productSet);
                editor.commit();

                Toast.makeText(getActivity(), "Product Added In Cart", Toast.LENGTH_LONG).show();
            }
        } else {

            SharedPreferences.Editor editor = sp.edit();
            productSet.add(productId);
            editor.putStringSet("ProductSet", productSet);
            editor.commit();

            Toast.makeText(getActivity(), "Product Added In Cart", Toast.LENGTH_LONG).show();
        }
    }
}
