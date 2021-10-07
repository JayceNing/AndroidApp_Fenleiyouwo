package com.example.fenleiyouwo.tabs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fenleiyouwo.R;

import java.util.List;

public class QuestionAdapter extends ArrayAdapter<questionI> {
    public QuestionAdapter(Context context, int resource, List<questionI> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 获取数据
        final questionI question = getItem(position);

        // 创建布局
        View oneQuestionView = LayoutInflater.from(getContext()).inflate(R.layout.question_item, parent, false);

        // 获取ImageView和TextView

        TextView textView = (TextView) oneQuestionView.findViewById(R.id.question_title);
        TextView content = (TextView) oneQuestionView.findViewById(R.id.question_content);
        com.loopj.android.image.SmartImageView siv = (com.loopj.android.image.SmartImageView) oneQuestionView.findViewById(R.id.questionArticlePic);
        // 根据数据设置ImageView和TextView的展现

        textView.setText(question.getTitle());
        content.setText(question.getContent());
        siv.setImageUrl(question.getPicUrl());
        oneQuestionView.setOnClickListener(new View.OnClickListener() {
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
                intent.putExtra("url",question.getArticleUrl());

                getContext().startActivity(intent);
            }
        });

        return oneQuestionView;
    }
}
