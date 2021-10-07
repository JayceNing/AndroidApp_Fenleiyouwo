package com.example.fenleiyouwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.fenleiyouwo.recycleview.Garbage;
import com.example.fenleiyouwo.recycleview.GarbageAdapter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicGarbageDetail extends Activity {
    @BindView(R.id.t2)
    QMUITopBar mTopBar;

    private List<Garbage> garbageList = new ArrayList<Garbage>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        Intent intent1=getIntent();
        Bundle bundle=intent1.getExtras();//.getExtras()得到 intent 所附带的额外数据
        String str=bundle.getString("garbageJsonData");//getString()返回指定 key 的值
        parseJSONWithJSONObject(str);//将上一页面传来的数据进行转换
        View root = LayoutInflater.from(this).inflate(R.layout.activity_picgarbagedetail, null);


        ButterKnife.bind(this, root);
        initTopBar();
        setContentView(root);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        GarbageAdapter adapter = new GarbageAdapter(garbageList);
        recyclerView.setAdapter(adapter);
    }

    private void initTopBar() {
        mTopBar.setBackgroundColor(ContextCompat.getColor(this, R.color.app_color_theme_5));
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                //overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
            }
        });

        mTopBar.setTitle("图片识别结果");
    }
    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject1 = new JSONObject(jsonData);
            //JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
            JSONArray jsonArray=jsonObject1.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String score = jsonObject.getString("score");
                String keyword = jsonObject.getString("keyword");
                Log.d("MainActivity", "图片可信度：" + score);
                Log.d("MainActivity", "图片内容：" + keyword);
                Garbage garbage = new Garbage("✨识别结果："+keyword, "结果可信度："+score);
                garbageList.add(garbage);
                boolean dataNJson =jsonObject.isNull("list");
                if(!dataNJson) {
                    JSONArray jsonArray1 = jsonObject.getJSONArray("list");
                    if(jsonArray1.length()>0) {
                        for (int j = 0; j < jsonArray1.length(); j++) {
                            JSONObject jsonObject3 = jsonArray1.getJSONObject(j);
                            String name = jsonObject3.getString("name");
                            String category = jsonObject3.getString("category");

                            Log.d("MainActivity", "垃圾名称：" + name);
                            Log.d("MainActivity", "垃圾类别：" + category);
                            Garbage garbage2 = new Garbage(name, category);
                            garbageList.add(garbage2);


                        }
                    }
                }


            }
//            JSONObject jsonObject1 = new JSONObject(jsonData);
//            JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
//            JSONArray jsonArray=jsonObject2.getJSONArray("list");
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                String name = jsonObject.getString("name");
//                String category = jsonObject.getString("category");
//                Log.d("MainActivity", "垃圾名称：" + name);
//                Log.d("MainActivity", "垃圾类别：" + category);
//                Garbage garbage = new Garbage(name, category);
//                garbageList.add(garbage);
//
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
