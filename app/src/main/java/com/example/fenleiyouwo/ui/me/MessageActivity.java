package com.example.fenleiyouwo.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;
import com.example.fenleiyouwo.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent intent1=getIntent();
        Bundle bundle=intent1.getExtras();
        String id=bundle.getString("id");
        Log.d("id",id);
        sendRequestWithOkHttp();
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
                            .build();
                    Request request = new Request.Builder()
                            .url("http://123.60.15.193/rubbish/index.php/Home/Message/get_person_message")
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
                    JSONObject jsonObject1=new JSONObject(jsonData);
                    int error_code=jsonObject1.getInt("error_code");
                    if(error_code==0) {
                        JSONArray jsonArray = jsonObject1.getJSONArray("data");
                        // String[] DustbinIdArray = new String[jsonArray.length()];
                        String[] WeightArray = new String[jsonArray.length()];
                        //String[] CategoryArray = new String[jsonArray.length()];
                        String[] TimeArray = new String[jsonArray.length()];
                        int[] CategoryArray = new int[jsonArray.length()];
                        int[] IsCorrectlyArray = new int[jsonArray.length()];

                        for(int i=0; i<jsonArray.length();i++) {
                            JSONObject jsonObject =(JSONObject) jsonArray.get(i);

                            String dustbin_id = jsonObject.getString("dustbin_id");
                            String weight = jsonObject.getString("weight");
                            String category = jsonObject.getString("category");
                            String time = jsonObject.getString("time");
                            String iscorrectlycategorized = jsonObject.getString("iscorrectlycategorized");
                    /*
                    Log.d("dustbin_id is", dustbin_id);
                    Log.d("weight is", weight);
                    Log.d("category is", category);
                    Log.d("time is", time);
                    Log.d("iscorrectlycategorized", iscorrectlycategorized);

                     */
                            //DustbinIdArray[i]=dustbin_id;
                            WeightArray[i]=weight;
                            TimeArray[i]=time;
                            switch (category) {
                                case "1":
                                    CategoryArray[i] = R.drawable.a;
                                    break;
                                case "2":
                                    CategoryArray[i] = R.drawable.b;
                                    break;
                                case "3":
                                    CategoryArray[i] = R.drawable.c;
                                    break;
                            }
                            if(iscorrectlycategorized.equals("1")){
                                IsCorrectlyArray[i]=R.drawable.duigou;
                            }
                            else if(iscorrectlycategorized.equals("2")){
                                IsCorrectlyArray[i]=R.drawable.chacha;
                            }
                            Log.d("corr", String.valueOf(IsCorrectlyArray[i]));
                            Log.d("category", String.valueOf(CategoryArray[i]));


                        }
                        List<Map<String,Object>> listitem=new ArrayList<Map<String,Object>>();
                        for(int i=0;i<WeightArray.length;i++){
                            Map<String,Object> showitem =new HashMap<String,Object>();
                            showitem.put("category",CategoryArray[i]);
                            showitem.put("weight",WeightArray[i]+"KG");
                            showitem.put("time",TimeArray[i]);
                            showitem.put("corr",IsCorrectlyArray[i]);
                            listitem.add(showitem);

                        }
                        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(),listitem,R.layout.item,
                                new String[]{"category","weight","time","corr"},
                                new int[]{R.id.category,R.id.weight,R.id.time,R.id.corr});
                        ListView listView = (ListView)findViewById(R.id.listView);
                        listView.setAdapter(myAdapter);


                        /*
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                MessageActivity.this,android.R.layout.simple_list_item_1,WeightArray
                        );
                        ListView listView = (ListView)findViewById(R.id.listView);
                        listView.setAdapter(adapter);
                         */


                    }
                    else {
                        String msg=jsonObject1.getString("msg");

                        Toast.makeText(MessageActivity.this,msg,Toast.LENGTH_SHORT).show();

                        Log.d("msg",msg);

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
