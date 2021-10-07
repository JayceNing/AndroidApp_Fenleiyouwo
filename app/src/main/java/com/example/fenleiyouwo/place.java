package com.example.fenleiyouwo;

import android.Manifest;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fenleiyouwo.recycleview.Garbage;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.LocationSource;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.IOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.MyLocationStyle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import pub.devrel.easypermissions.EasyPermissions;

public class place extends AppCompatActivity{

    /**
     * 由于SDK并没有提供用于MapView管理地图生命周期的Activity
     * 因此需要用户继承Activity后管理地图的生命周期，防止内存泄露
     */
    private TencentLocationManager locationManager;
    private TencentLocationRequest locationRequest;
    private MyLocationStyle locationStyle;
    private MapView mapView;
    protected TencentMap tencentMap;
    private TencentLocationListener locationChangedListener;
    private List<LatLng> latLngs = new ArrayList<>();
    private List<IOverlay> overlays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        mapView = findViewById(R.id.mapview);
        //创建tencentMap地图对象，可以完成对地图的几乎所有操作
        tencentMap = mapView.getMap();


        sendRequestWithHttpURLConnection();


//        latLngs.add(new LatLng(39.96085, 116.357394));
//        latLngs.add(new LatLng(39.95885, 116.357394));
//
//
//        tencentMap.addMarker(new MarkerOptions(latLngs.get(0))
//                .anchor(0.5f, 1).title("垃圾桶3"));
//        tencentMap.addMarker(new MarkerOptions(latLngs.get(1))
//                .anchor(0.5f, 1).title("垃圾桶2"));

//        LatLng center = new LatLng(39.95985, 116.357394);
//        tencentMap.addMarker(new MarkerOptions(center)
//                .anchor(0.5f, 1).title("中心"));
//        tencentMap.moveCamera(CameraUpdateFactory.newLatLngBoundsWithMapCenter(
//                new LatLngBounds.Builder()
//                        .include(latLngs)
//                        .build(),
//                center,
//                100));

//        tencentMap.setMyLocationEnabled(true);
//        locationManager = TencentLocationManager.getInstance(this);
//        //设置坐标系
//        locationManager.setCoordinateType(TencentLocationManager.COORDINATE_TYPE_GCJ02);
//        //创建定位请求
//        locationRequest = TencentLocationRequest.create();
//        //设置定位周期（位置监听器回调周期）为3s
//        locationRequest.setInterval(1000);
//
//        //地图上设置定位数据源
//        //tencentMap.setLocationSource(this);
//        //设置当前位置可见
//        tencentMap.setMyLocationEnabled(true);
//        //设置定位图标样式
//        tencentMap.setMyLocationStyle(locationStyle);

    }
    private void sendRequestWithHttpURLConnection() {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("http://123.60.15.193/rubbish/index.php/Home/Map/get_all");
                    //URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    //connection.setRequestProperty("Authorization", "APPCODE 850d4015461548eeb7b24cafa9be5acf");
                    //connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
                    //connection.setRequestProperty("name", input);
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
                    Log.d(response.toString(), "垃圾桶信息: ");
                    try {
                        JSONObject jsonObject1 = new JSONObject(response.toString());
                        //JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
                        JSONArray jsonArray=jsonObject1.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String dustbin_id = jsonObject.getString("dustbin_id");
                            String longitude = jsonObject.getString("longitude");
                            String latitude = jsonObject.getString("latitude");
                            String state1 = jsonObject.getString("state1");
                            String state2 = jsonObject.getString("state2");
                            String state3 = jsonObject.getString("state3");
                            Log.d("MainActivity", "垃圾桶编号：" + dustbin_id);
                            Log.d("MainActivity", "longitude：" + longitude);
                            Log.d("MainActivity", "latitude：" + latitude);
                            Log.d("MainActivity", "state1：" + state1);
                            Log.d("MainActivity", "state2：" + state2);
                            Log.d("MainActivity", "state3：" + state3);
                            String s1;
                            String s2;
                            String s3;

                            if(state1.equals("1")){
                                s1="未满";
                            }else{
                                s1="已满";
                            }
                            if(state2.equals("1")){
                                s2="未满";
                            }else{
                                s2="已满";
                            }
                            if(state3.equals("1")){
                                s3="未满";
                            }else{
                                s3="已满";
                            }
                            BitmapDescriptor custom = BitmapDescriptorFactory.fromResource(R.drawable.trashbin);
                            latLngs.add(new LatLng(Double.valueOf(latitude), Double.valueOf(longitude)));
                            Marker marker1=tencentMap.addMarker(new MarkerOptions(new LatLng(Double.valueOf(latitude), Double.valueOf(longitude))).icon(custom)
                                    .alpha(0.9f)
                                    .flat(true)
                                    .clockwise(false)
                                    .rotation(0)
                                    .anchor(0.5f, 1).title("垃圾桶"+dustbin_id).snippet("可回收：" + s1+" 厨余：" + s2+" 其他：" + s3));
                            overlays.add(marker1);

                        }
                        LatLng center = new LatLng(39.9615, 116.358);

                        tencentMap.moveCamera(CameraUpdateFactory.newLatLngBoundsWithMapCenter(
                                new LatLngBounds.Builder()
                                        .include(latLngs)
                                        .build(),
                                center,
                                100));


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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

    /**
     * mapview的生命周期管理
     */
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mapView.onRestart();
    }



}
