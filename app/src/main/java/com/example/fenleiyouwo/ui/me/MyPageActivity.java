package com.example.fenleiyouwo.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fenleiyouwo.R;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MyPageActivity extends AppCompatActivity {

    private List<Items> itemlist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        Intent intent1=getIntent();
        Bundle bundle=intent1.getExtras();//.getExtras()得到 intent 所附带的额外数据
        String id=bundle.getString("id");//getString()返回指定 key 的值
        String username = bundle.getString("username");
        String phone = bundle.getString("phone");
        String water=bundle.getString("water");
        String fertilizer=bundle.getString("fertilizer");
        String tree_progress=bundle.getString("tree_progress");
        //Log.d("username", username);

        TextView textView=(TextView)findViewById(R.id.tips);
        ImageView imageView=(ImageView)findViewById(R.id.touxiang);
        ImageView imageView2=(ImageView)findViewById(R.id.logo);
        imageView.setImageResource(R.drawable.touxiang);
        imageView2.setImageResource(R.drawable.logo);
        textView.setText(username);
        if(!username.equals("未登录")){
            Button btn=(Button)findViewById(R.id.login);
            btn.setText("注销");
        }
        inititems();
        ItemsAdapter adapter=new ItemsAdapter(MyPageActivity.this,R.layout.itemlayout,itemlist);
        ListView listview=(ListView)findViewById(R.id.list_view);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Items items=itemlist.get(position);
                String user_id=getIntent().getStringExtra("id");
                String water=getIntent().getStringExtra("water");
                String fertilizer=getIntent().getStringExtra("fertilizer");
                String tree_progress=getIntent().getStringExtra("tree_progress");
                switch (items.getId())
                {
                    case 1:

                        Intent intent=new Intent(MyPageActivity.this ,  MessageActivity.class);
                        intent.putExtra("id",user_id);
                        startActivity(intent);
                        break;
                    case 2:

                        Intent intent2 = new Intent(MyPageActivity.this, TreeActivity.class);
                        intent2.putExtra("id",user_id);
                        intent2.putExtra("water",water);
                        intent2.putExtra("fertilizer",fertilizer);
                        intent2.putExtra("tree_progress",tree_progress);
                        startActivity(intent2);
                        break;
                    case 3:
                        Toast.makeText(MyPageActivity.this,"V0.9.1",Toast.LENGTH_SHORT).show();
                        break;



                }
            }
        });

    }
    private void inititems() {
        Items one=new Items("投放记录查询",R.drawable.pone,1);
        itemlist.add(one);
        Items two=new Items("我的种树",R.drawable.ptwo,2);
        itemlist.add(two);
        Items three=new Items("版本",R.drawable.pthree,3);
        itemlist.add(three);
        Items four=new Items("帮助",R.drawable.pfour,4);
        itemlist.add(four);
    }

    public void login(View v){
        Intent intent = new Intent(this, MeActivity.class);
        setResult(RESULT_OK, intent);
        finish();

    }
    public void inquire(View v){
        String id=getIntent().getStringExtra("id");
        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);

    }
    public void gotree(View v){
        String id=getIntent().getStringExtra("id");
        String water=getIntent().getStringExtra("water");
        String fertilizer=getIntent().getStringExtra("fertilizer");
        String tree_progress=getIntent().getStringExtra("tree_progress");
        Intent intent = new Intent(this, TreeActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("water",water);
        intent.putExtra("fertilizer",fertilizer);
        intent.putExtra("tree_progress",tree_progress);


        startActivity(intent);

    }

}