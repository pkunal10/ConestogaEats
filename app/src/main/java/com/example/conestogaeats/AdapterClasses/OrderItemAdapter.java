package com.example.conestogaeats.AdapterClasses;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.conestogaeats.DBHelperClasses.MainDBHelperClass;
import com.example.conestogaeats.Models.Dish;
import com.example.conestogaeats.Models.OrderItem;
import com.example.conestogaeats.R;

import java.util.ArrayList;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemViewHolder> {
    Context context;
    ArrayList<OrderItem> orderItems;
    MainDBHelperClass mainDBHelperClass;

    public OrderItemAdapter(Context context,ArrayList<OrderItem> orderItems)
    {
        this.context=context;
        this.orderItems=orderItems;
        mainDBHelperClass=new MainDBHelperClass(context);
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrderItemViewHolder(LayoutInflater.from(context).inflate(R.layout.oh_order_item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder orderItemViewHolder, int i) {
        OrderItem orderItem=orderItems.get(i);
        Dish dish=new Dish();
        dish=mainDBHelperClass.getDishById(orderItem.getDishId());

        String prName=dish.getName().substring(0,3);
        prName=prName.substring(0,1).toLowerCase()+prName.substring(1,3);

        String img="com.example.conestogaeats:drawable/"+prName;
        int res=context.getResources().getIdentifier(img,null,null);

        orderItemViewHolder.imgDish.setImageResource(res);

        Float total=0.0f;
        total=Float.parseFloat(dish.getPrice())*Integer.parseInt(orderItem.getQty());

        orderItemViewHolder.txtDishName.setText(dish.getName());
        orderItemViewHolder.txtDishPrice.setText("Total: $"+total);
        orderItemViewHolder.txtQty.setText("Qty: "+orderItem.getQty());

    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }
}
