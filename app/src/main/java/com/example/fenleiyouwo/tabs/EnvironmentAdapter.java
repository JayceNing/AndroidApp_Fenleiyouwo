package com.example.fenleiyouwo.tabs;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.fenleiyouwo.R;
import java.util.List;

public class EnvironmentAdapter extends ArrayAdapter<EnvironmentI> {
    public EnvironmentAdapter(Context context, int resource, List<EnvironmentI> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 获取数据
        final EnvironmentI environment = getItem(position);

        // 创建布局
        View oneEnvironmentView = LayoutInflater.from(getContext()).inflate(R.layout.environment_item, parent, false);

        // 获取ImageView和TextView

        TextView textView = (TextView) oneEnvironmentView.findViewById(R.id.environment_title);
        TextView content = (TextView) oneEnvironmentView.findViewById(R.id.environment_content);
        com.loopj.android.image.SmartImageView siv = (com.loopj.android.image.SmartImageView) oneEnvironmentView.findViewById(R.id.articlePic);
        // 根据数据设置ImageView和TextView的展现

        textView.setText(environment.getTitle());
        content.setText(environment.getContent());
        siv.setImageUrl(environment.getPicUrl());
        oneEnvironmentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //  初始化一个准备跳转到TeacherDetailActivity的Intent
//                Intent intent = new Intent(getContext(), EnvironmentDetailActivity.class);
//
//                // 往Intent中传入Teacher相关的数据，供TeacherDetailActivity使用
//                intent.putExtra("teacher_image", environment.getImageId());
//                intent.putExtra("teacher_desc", environment.getDesc());
//
//                //  初始化一个准备跳转到TeacherDetailActivity的Intent
//                getContext().startActivity(intent);
                Intent intent = new Intent(getContext(),WebViewActivity.class);
                intent.putExtra("url",environment.getArticleUrl());

                getContext().startActivity(intent);
            }
        });

        return oneEnvironmentView;
    }
}
