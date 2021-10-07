package com.example.fenleiyouwo.ui.me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fenleiyouwo.R;

import java.util.List;

public class ItemsAdapter extends ArrayAdapter<Items> {

    private int resourceId;

    public ItemsAdapter(Context context, int textViewResourceId,
                        List<Items> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Items item = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById (R.id.sword);
            viewHolder.fruitName = (TextView) view.findViewById (R.id.item_name);
            viewHolder.fruitImage2=(ImageView)view.findViewById(R.id.image) ;
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.fruitImage2.setImageResource(item.getImageId());
        viewHolder.fruitImage.setImageResource(R.drawable.sword);
        viewHolder.fruitName.setText(item.getName());
        return view;
    }

    class ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        ImageView fruitImage2;
    }

}
