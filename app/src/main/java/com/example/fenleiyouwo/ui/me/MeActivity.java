package com.example.fenleiyouwo.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fenleiyouwo.Data;
import com.example.fenleiyouwo.R;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        Button login = (Button) findViewById(R.id.login);
        Button sign =(Button) findViewById(R.id.sign);
        EditText username = (EditText) findViewById(R.id.username);
        String str=getIntent().getStringExtra("username");//getString()返回指定 key 的值
        //if(!str.equals(" ")) {
        username.setText(str);
        //}

    }
    public void guest(View v){

        Intent intent = new Intent(this, MyPageActivity.class);
        intent.putExtra("id", "0");
        intent.putExtra("username", "未登录");
        intent.putExtra("phone", "0");
        intent.putExtra("face_url", "0");
        intent.putExtra("water", "0");
        intent.putExtra("fertilizer", "0");
        intent.putExtra("tree_progress", "0");
        startActivityForResult(intent,1);


    }
    public void gosign(View v){
        Intent intent = new Intent(this, SignActivity.class);
        startActivity(intent);

        //finish();
    }

    public void gomypage(View v){
        Log.d("you press","login");

        sendRequestWithOkHttp();

    }
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String username=((EditText)findViewById(R.id.username)).getText().toString();
                    String passwd = ((EditText)findViewById(R.id.password)).getText().toString();
                    Log.d("print username",username);
                    Log.d("print password",passwd);
                    OkHttpClient client= new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("username",username)
                            .add("password",passwd)
                            .build();
                    Request request = new Request.Builder()
                            .url("http://123.60.15.193/rubbish/index.php/Home/User/login")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    //showResponse(responseData);
                    parseJSONWithJSONObject(responseData);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONWithJSONObject(String jsonData){
        try{
            JSONObject jsonObject1=new JSONObject(jsonData);
            int error_code=jsonObject1.getInt("error_code");
            if(error_code==0) {
                JSONObject jsonObject = jsonObject1.getJSONObject("data");

                String id = jsonObject.getString("id");
                String username = jsonObject.getString("username");
                String phone = jsonObject.getString("phone");
                String face_url = jsonObject.getString("face_url");
                String water = jsonObject.getString("water");
                String fertilizer = jsonObject.getString("fertilizer");
                String tree_progress = jsonObject.getString("tree_progress");
                Log.d("id is", id);
                Log.d("username is", username);
                Log.d("phone is", phone);
                Log.d("face_url is", face_url);
                Log.d("water is", water);
                Log.d("fertilizer is", fertilizer);
                Log.d("tree_progress is", tree_progress);
                final Data app = (Data)getApplication();
                app.setid(id);
                app.setusername(username);
                app.setphoneN(phone);
                app.setface_url(face_url);
                app.setwaterN(water);
                app.setfertilizer(fertilizer);
                app.settree_progress(tree_progress);

                finish();
//                Intent intent = new Intent(this, MyPageActivity.class);
//                intent.putExtra("id", id);
//                intent.putExtra("username", username);
//                intent.putExtra("phone", phone);
//                intent.putExtra("face_url", face_url);
//                intent.putExtra("water", water);
//                intent.putExtra("fertilizer", fertilizer);
//                intent.putExtra("tree_progress", tree_progress);
//                startActivity(intent);
            }
            else {
                String msg=jsonObject1.getString("msg");
                Looper.prepare();
                Toast.makeText(MeActivity.this,msg,Toast.LENGTH_SHORT).show();
                Looper.loop();
                Log.d("msg",msg);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
