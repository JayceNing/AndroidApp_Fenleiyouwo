package com.example.fenleiyouwo.ui.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fenleiyouwo.Data;
import com.example.fenleiyouwo.R;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private List<Item> mItemList;


    static class ViewHolder extends RecyclerView.ViewHolder {
        View ItemView;
        ImageView ItemImage;
        TextView ItemName;
        TextView ItemPrice;

        public ViewHolder(View view) {
            super(view);
            ItemView = view;
            ItemImage = (ImageView) view.findViewById(R.id.item_image);
            ItemName = (TextView) view.findViewById(R.id.item_name);
            ItemPrice=(TextView)view.findViewById(R.id.item_price);
        }
    }

    public ItemAdapter(List<Item> ItemList) {
        mItemList = ItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.ItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Item item = mItemList.get(position);
                Toast.makeText(v.getContext(), "你购买了 " + item.getName(), Toast.LENGTH_SHORT).show();

            }
        });

        holder.ItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Item item = mItemList.get(position);
                Toast.makeText(v.getContext(), "你购买了 " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = mItemList.get(position);
        holder.ItemImage.setImageResource(item.getImageId());
        holder.ItemName.setText(item.getName());
        holder.ItemPrice.setText(item.getPrice());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
