package com.example.fenleiyouwo.recycleview;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fenleiyouwo.R;

import java.util.List;

public class GarbageAdapter extends RecyclerView.Adapter<GarbageAdapter.ViewHolder>{
    private List<Garbage> mGarbageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View garbageView;
        TextView garbageName;
        TextView garbageCategory;

        public ViewHolder(View view) {
            super(view);
            garbageView = view;
            garbageName = (TextView) view.findViewById(R.id.garbage_name);
            garbageCategory = (TextView) view.findViewById(R.id.garbage_category);
        }
    }

    public GarbageAdapter(List<Garbage> garbageList) {
        mGarbageList = garbageList;
    }

    @Override
    public GarbageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.garbage_item, parent, false);
        final GarbageAdapter.ViewHolder holder = new GarbageAdapter.ViewHolder(view);

        holder.garbageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Garbage garbage = mGarbageList.get(position);
                Toast.makeText(v.getContext(), "你点击了 " + garbage.getName(), Toast.LENGTH_SHORT).show();

            }
        });
        holder.garbageCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Garbage garbage = mGarbageList.get(position);
                Toast.makeText(v.getContext(), "该垃圾的分类为 " + garbage.getCategory(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(GarbageAdapter.ViewHolder holder, int position) {
        Garbage garbage = mGarbageList.get(position);
        holder.garbageName.setText(garbage.getName());
        holder.garbageCategory.setText(garbage.getCategory());

        if(garbage.getCategory().equals("可回收垃圾")) {
            holder.itemView.setBackgroundColor(Color.parseColor("#6495ED"));
        }else if(garbage.getCategory().equals("有害垃圾")) {
            holder.itemView.setBackgroundColor(Color.parseColor("#EF5362"));
        }else if(garbage.getCategory().equals("湿垃圾")) {
            holder.itemView.setBackgroundColor(Color.parseColor("#9FD661"));
        }

    }

    @Override
    public int getItemCount() {
        return mGarbageList.size();
    }
}
