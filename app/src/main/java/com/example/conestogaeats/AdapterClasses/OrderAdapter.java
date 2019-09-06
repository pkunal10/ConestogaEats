package com.example.conestogaeats.AdapterClasses;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.conestogaeats.DBHelperClasses.MainDBHelperClass;
import com.example.conestogaeats.Models.Dish;
import com.example.conestogaeats.Models.Order;
import com.example.conestogaeats.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    Context context;
    ArrayList<Order> orders;
    MainDBHelperClass mainDBHelperClass;

    public OrderAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
        mainDBHelperClass = new MainDBHelperClass(context);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrderViewHolder(LayoutInflater.from(context).inflate(R.layout.order_item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderViewHolder orderViewHolder, int i) {
        final Order order = orders.get(i);

        orderViewHolder.ohOdrDate.setText(order.getOrderdate());
        orderViewHolder.ohTotalItems.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        orderViewHolder.ohTotalItems.setText("( " + order.getOrderItems().size() + " Items )");

        Float grandTotal = 0.0f;
        for (int j = 0; j < order.getOrderItems().size(); j++) {
            Dish dish = new Dish();
            dish = mainDBHelperClass.getDishById(order.getOrderItems().get(j).getDishId());
            grandTotal += Integer.parseInt(order.getOrderItems().get(j).getQty()) * Float.parseFloat(dish.getPrice());

        }
        orderViewHolder.ohTotalBill.setText("Bill: $" + grandTotal);

        orderViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(orderViewHolder.rvOHOI.getVisibility()==View.GONE) {
                    orderViewHolder.rvOHOI.setVisibility(View.VISIBLE);
                    Animation animation = (Animation) AnimationUtils.loadAnimation(context, R.anim.slide_down);
                    orderViewHolder.rvOHOI.setAnimation(animation);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    OrderItemAdapter orderItemAdapter = new OrderItemAdapter(context, order.getOrderItems());
                    orderViewHolder.rvOHOI.setLayoutManager(linearLayoutManager);
                    orderViewHolder.rvOHOI.setAdapter(orderItemAdapter);
                }
                else
                {
                    orderViewHolder.rvOHOI.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
