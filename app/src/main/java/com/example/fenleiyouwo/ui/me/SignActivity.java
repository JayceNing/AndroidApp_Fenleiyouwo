package com.example.fenleiyouwo.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.fenleiyouwo.R;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
    }
    public void gomain(View v){
        Log.d("you press","tijiao");

        sendRequestWithOkHttp();

    }
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String username=((EditText)findViewById(R.id.username)).getText().toString();
                    String phone = ((EditText)findViewById(R.id.phone)).getText().toString();
                    //String face_url = ((EditText)findViewById(R.id.face_url)).getText().toString();
                    String password = ((EditText)findViewById(R.id.password)).getText().toString();
                    String password_again = ((EditText)findViewById(R.id.password_again)).getText().toString();
                    String student_ID = ((EditText)findViewById(R.id.student_ID)).getText().toString();
                    //Log.d("print username",username);
                    //Log.d("print password",password);
                    OkHttpClient client= new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("username",username)
                            .add("phone",phone)
                            .add("face_url","touxiang.jpg")
                            .add("password",password)
                            .add("password_again",password_again)
                            .add("student_ID",student_ID)
                            .build();
                    Request request = new Request.Builder()
                            .url("http://123.60.15.193/rubbish/index.php/Home/User/sign")
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
            Log.d("Test","hello");
            JSONObject jsonObject1=new JSONObject(jsonData);
            Log.d("jsonData",jsonData);
            int error_code=jsonObject1.getInt("error_code");
            if(error_code==0) {
                JSONObject jsonObject = jsonObject1.getJSONObject("data");
                Log.d("jsonobject", String.valueOf(jsonObject));

                String username = jsonObject.getString("username");


                Log.d("username is", username);

                Intent intent = new Intent(this, MeActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);

                finish();
            }
            else {
                String msg=jsonObject1.getString("msg");
                Looper.prepare();
                Toast.makeText(SignActivity.this,msg,Toast.LENGTH_SHORT).show();
                Looper.loop();
                Log.d("msg",msg);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
