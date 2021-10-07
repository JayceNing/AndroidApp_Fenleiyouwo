package com.example.fenleiyouwo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.fenleiyouwo.recycleview.Fruit;
import com.example.fenleiyouwo.recycleview.FruitAdapter;
import com.example.fenleiyouwo.recycleview.Garbage;
import com.example.fenleiyouwo.recycleview.GarbageAdapter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class knowLedge extends Activity implements View.OnClickListener{
    @BindView(R.id.t1)
    QMUITopBar mTopBar;
    @BindView(R.id.send_request)
    Button sendRequest;
    @BindView(R.id.pic)
    View pic;
//    @BindView(R.id.response_text)
//    TextView responseText;

    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        //imageView = (ImageView) findViewById(R.id.image1);
        View root = LayoutInflater.from(this).inflate(R.layout.activity_knowledge, null);

        //responseText = (TextView) findViewById(R.id.response_text);

        ButterKnife.bind(this, root);
        initTopBar();
        setContentView(root);

        //initFruits();
        //initG();



    }
//    private void initG() {
//        for (int i = 0; i < 2; i++) {
//            Garbage apple = new Garbage("Apple", "Apple");
//            garbageList.add(apple);
//            Garbage banana = new Garbage("banana", "banana");
//            garbageList.add(banana);
//            Garbage bing = new Garbage("bing", "bing");
//            garbageList.add(bing);
//
//        }
//    }
    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("Banana"), R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit(getRandomLengthName("Orange"), R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit(getRandomLengthName("Watermelon"), R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit(getRandomLengthName("Pear"), R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit(getRandomLengthName("Grape"), R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit(getRandomLengthName("Pineapple"), R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(getRandomLengthName("Strawberry"), R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit(getRandomLengthName("Cherry"), R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit(getRandomLengthName("Mango"), R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }
    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
    @OnClick(R.id.send_request)
    public void onViewClicked() {
        String input=((EditText)findViewById(R.id.et1)).getText().toString();
        //Toast.makeText(knowLedge.this,input,Toast.LENGTH_SHORT).show();
        if(input.length()>0) {

            //garbageList.clear();
            sendRequestWithHttpURLConnection(input);
        }else{
            Toast.makeText(knowLedge.this,"请输入垃圾名称",Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.pic)
    public void onPicClicked() {
//        Intent intent = new Intent(this, takePhoto.class);
//
//        startActivity(intent);

        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent, 0); // 打开相册

    }
    private ImageView picture;

    private Uri imageUri;
    private TextView image_base64;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        picture = (ImageView) findViewById(R.id.picture);
        image_base64=(TextView)findViewById(R.id.image_base64);
        Log.d(data.getDataString(), "选择照片: ");

        Uri uri = data.getData();
        picture.setImageURI(uri);

        try {
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(
//                    this.getContentResolver(), uri);
            Bitmap bitmap=getThumbnail(uri,120);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,30,bos);
            byte[] bb = bos.toByteArray();
            String imgBase64 = Base64.encodeToString(bb, Base64.NO_WRAP);
            Log.d(imgBase64, "照片的imagebase64为: ");
            //String str = new String(imgBase64.getBytes(), "UTF-8");
            String str = URLEncoder.encode(imgBase64, "UTF-8");

            PicAnalyze(str);
            //image_base64.setText(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Bitmap getThumbnail(Uri uri, int size) throws FileNotFoundException, IOException{
        InputStream input = this.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither=true;//optional
        onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
            return null;
        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;
        double ratio = (originalSize > size) ? (originalSize / size) : 1.0;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither=true;//optional
        bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        input = this.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }
    private static int getPowerOfTwoForSampleRatio(double ratio){
        int k = Integer.highestOneBit((int)Math.floor(ratio));
        if(k==0) return 1;
        else return k;
    }
    public String getImgBase64(ImageView imageView){

        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
        byte[] bb = bos.toByteArray();
        String imgbase64 = Base64.encodeToString(bb, Base64.NO_WRAP);
        return imgbase64;
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

        mTopBar.setTitle("分类知识");

    }


    @Override
    public void onClick(View v) {

    }
    private void sendRequestWithHttpURLConnection(String input) {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection connection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("https://recover2.market.alicloudapi.com/recover_word");
                    //URL url = new URL("https://www.baidu.com");
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Authorization", "APPCODE 850d4015461548eeb7b24cafa9be5acf");
                    connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
                    connection.setRequestProperty("name", input);
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    connection.getContent();
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    //showResponse(response.toString());
                    toGarbageDetail(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    private void PicAnalyze(String input) {
        // 开启线程来发起网络请求
        Log.d(input, "PicAnalyze输入参数为: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection connection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("https://recover.market.alicloudapi.com/recover");
                    //URL url = new URL("https://www.baidu.com");
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Authorization", "APPCODE 850d4015461548eeb7b24cafa9be5acf");
                    connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
                    connection.setRequestProperty("img", input);
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    connection.getContent();
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    //showResponse(response.toString());
                    Log.d(response.toString(), "图片识别结果为: ");
                    toPicGarbageDetail(response.toString());
//                    try {
//                        JSONObject jsonObject1 = new JSONObject(response.toString());
//                        //JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
//                        JSONArray jsonArray=jsonObject1.getJSONArray("data");
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject jsonObject = jsonArray.getJSONObject(i);
//                            String score = jsonObject.getString("score");
//                            String keyword = jsonObject.getString("keyword");
//                            Log.d("MainActivity", "图片可信度：" + score);
//                            Log.d("MainActivity", "图片内容：" + keyword);
//                            boolean dataNJson =jsonObject.isNull("list");
//                            if(!dataNJson) {
//                                JSONArray jsonArray1 = jsonObject.getJSONArray("list");
//                                if(jsonArray1.length()>0) {
//                                    for (int j = 0; j < jsonArray1.length(); j++) {
//                                        JSONObject jsonObject3 = jsonArray1.getJSONObject(j);
//                                        String name = jsonObject3.getString("name");
//                                        String category = jsonObject3.getString("category");
//
//                                        Log.d("MainActivity", "垃圾名称：" + name);
//                                        Log.d("MainActivity", "垃圾类别：" + category);
//
//
//                                    }
//                                }
//                            }
//
//
//                        }
//
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }


                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    private void toGarbageDetail(String jsonData) {

            Intent intent = new Intent(this, garbageDetail.class);
            intent.putExtra("garbageJsonData", jsonData);
            startActivity(intent);

    }
    private void toPicGarbageDetail(String jsonData) {

        Intent intent = new Intent(this, PicGarbageDetail.class);
        intent.putExtra("garbageJsonData", jsonData);
        startActivity(intent);

    }
    private void showResponse(final String response) {
        Log.d(response, "图片识别结果为: ");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                //responseText.setText(response);


            }
        });
    }
}
