package com.example.fenleiyouwo.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fenleiyouwo.R;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TreeActivity extends AppCompatActivity {
    public ImageView imageView;
    private ProgressBar pb1;
    public TextView text1;
    public TextView text2;
    public TextView text3;
    String water;
    String fertilizer;
    String tree_progress;
    int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
        Intent intent1=getIntent();
        Bundle bundle=intent1.getExtras();
        water=bundle.getString("water");
        fertilizer=bundle.getString("fertilizer");
        tree_progress=bundle.getString("tree_progress");
        pb1 = (ProgressBar) findViewById(R.id.progressBar1);
        text1=(TextView)findViewById(R.id.num);
        text2= findViewById(R.id.num2);
        text3= findViewById(R.id.num3);
        text2.setText(water+"个");
        text3.setText(fertilizer+"个");
        pb1.setMax(1000);
        progress=Integer.parseInt(tree_progress);
        pb1.setProgress(progress);
        text1.setText(tree_progress);
        //Log.d("progress", String.valueOf(progress));
        imageView = (ImageView)findViewById(R.id.image1);
        if(progress<100){
            int resource = R.drawable.tree1;
            Glide.with(this).load(resource).into(imageView);
        }
        else if(100<progress&&progress<300){
            int resource = R.drawable.tree2;
            Glide.with(this).load(resource).into(imageView);
        }
        else if(300<progress&&progress<600){
            int resource = R.drawable.tree5;
            Glide.with(this).load(resource).into(imageView);
        }
        else if(600<progress){
            int resource = R.drawable.tree7;
            Glide.with(this).load(resource).into(imageView);
        }

    }


    public void water(View v){

        sendRequestWithOkHttp();
        text2.setText(water+"个");
        progress=Integer.parseInt(tree_progress);
        pb1.setProgress(progress);
        text1.setText(tree_progress);
        //Log.d("progress", String.valueOf(progress));
        imageView = (ImageView)findViewById(R.id.image1);
        if(progress<100){
            int resource = R.drawable.tree1;
            Glide.with(this).load(resource).into(imageView);
        }
        else if(100<progress&&progress<300){
            int resource = R.drawable.tree2;
            Glide.with(this).load(resource).into(imageView);
        }
        else if(300<progress&&progress<600){
            int resource = R.drawable.tree5;
            Glide.with(this).load(resource).into(imageView);
        }
        else if(600<progress){
            int resource = R.drawable.tree7;
            Glide.with(this).load(resource).into(imageView);
        }
    }
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String id=getIntent().getStringExtra("id");
                    OkHttpClient client= new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("id",id)
                            .add("water","1")
                            .build();
                    Request request = new Request.Builder()
                            .url("http://123.60.15.193/rubbish/index.php/Home/Tree/water")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONWithJSONObject(String jsonData){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    JSONObject jsonObject=new JSONObject(jsonData);
                    if(!water.equals("0")) {
                        water = jsonObject.getString("water");
                        tree_progress = jsonObject.getString("tree_progress");
                        //text2.setText(water+"个");
                    }
                    else{
                        String msg=jsonObject.getString("msg");
                        Toast.makeText(TreeActivity.this,msg,Toast.LENGTH_SHORT).show();
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }

    public void fertilizer(View v){
        sendRequestWithOkHttp2();
        text3.setText(fertilizer+"个");
        progress=Integer.parseInt(tree_progress);
        pb1.setProgress(progress);
        text1.setText(tree_progress);
        //Log.d("progress", String.valueOf(progress));
        imageView = (ImageView)findViewById(R.id.image1);
        if(progress<100){
            int resource = R.drawable.tree1;
            Glide.with(this).load(resource).into(imageView);
        }
        else if(100<progress&&progress<300){
            int resource = R.drawable.tree2;
            Glide.with(this).load(resource).into(imageView);
        }
        else if(300<progress&&progress<600){
            int resource = R.drawable.tree5;
            Glide.with(this).load(resource).into(imageView);
        }
        else if(600<progress){
            int resource = R.drawable.tree7;
            Glide.with(this).load(resource).into(imageView);
        }
    }
    private void sendRequestWithOkHttp2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String id=getIntent().getStringExtra("id");
                    OkHttpClient client= new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("id",id)
                            .add("fertilizer","1")
                            .build();
                    Request request = new Request.Builder()
                            .url("http://123.60.15.193/rubbish/index.php/Home/Tree/fertilizer")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject2(responseData);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONWithJSONObject2(String jsonData){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    JSONObject jsonObject=new JSONObject(jsonData);
                    if(!fertilizer.equals("0")) {

                        fertilizer = jsonObject.getString("fertilizer");
                        tree_progress = jsonObject.getString("tree_progress");
                    }
                    else {
                        String msg=jsonObject.getString("msg");
                        Toast.makeText(TreeActivity.this,msg,Toast.LENGTH_SHORT).show();
                    }



                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }

}
