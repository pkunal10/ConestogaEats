package com.example.conestogaeats.AdapterClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conestogaeats.CartActivity;
import com.example.conestogaeats.Models.Dish;
import com.example.conestogaeats.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    Context context;
    ArrayList<Dish> dishes;
    SharedPreferences sp;
    //Set<String> productSet = new HashSet<>();
    CartActivity cartActivity;

    public CartAdapter(Context context, ArrayList<Dish> dishes) {
        this.context = context;
        this.dishes = dishes;
        sp=context.getSharedPreferences("CE",Context.MODE_PRIVATE);
       // productSet=sp.getStringSet("ProductSet",null);
        cartActivity = (CartActivity) context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_rv_item_layout, viewGroup, false);
        CartViewHolder cartViewHolder = new CartViewHolder(view);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder cartViewHolder, final int i) {
        final Dish product = dishes.get(i);
        // cartViewHolder.ivCartProduct.setImageResource(product.getImage());

        String prName=product.getName().substring(0,3);
        prName=prName.substring(0,1).toLowerCase()+prName.substring(1,3);

        String img="com.example.conestogaeats:drawable/"+prName;
        int res=context.getResources().getIdentifier(img,null,null);

        cartViewHolder.ivCartProduct.setImageResource(res);

        cartViewHolder.tvCartProductName.setText(product.getName());
        cartViewHolder.tvCartPrice.setText("$" + product.getPrice());
        cartViewHolder.dishId.setText(product.getId());
        getProductTotal(product, cartViewHolder);
        cartViewHolder.etCartQty.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int qty = 0;
                try {
                    qty = Integer.parseInt(cartViewHolder.etCartQty.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    getProductTotal(product, cartViewHolder);
                }
                if (qty <= 0) {
                    Toast.makeText(context, "Enter valid quantity.", Toast.LENGTH_SHORT).show();
                    cartViewHolder.etCartQty.setText("1");
                    getProductTotal(product, cartViewHolder);
                } else {
                    getProductTotal(product, cartViewHolder);
                }

            }
        });

        cartViewHolder.btnCartDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dishes.remove(i);
                notifyDataSetChanged();

                Set<String> set = new HashSet<String>();
                set = sp.getStringSet("ProductSet", null);
                set.remove(product.getId());
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("ProductSet");
                editor.putStringSet("ProductSet", set);
                editor.commit();

                cartActivity.CheckEmptyCart();
                cartActivity.calculateGrandTotal();
            }
        });
    }


    private void getProductTotal(Dish product, CartViewHolder cartViewHolder) {
        cartViewHolder.tvCartProductTotalPrice.setText("Total: $" + Float.parseFloat(product.getPrice()) * Integer.parseInt(cartViewHolder.etCartQty.getText().toString()));
        cartActivity.calculateGrandTotal();
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }
}
