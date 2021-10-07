package com.example.fenleiyouwo.tabs;

import android.util.Log;

import com.example.fenleiyouwo.R;

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

public class EnvironmentI {
    private String title;
    private String content;
    private String picUrl;
    private String articleUrl;



    //构造函数
    public EnvironmentI(String title, String content, String picurl,String articalurl){
        this.title=title;
        this.content=content;
        this.picUrl=picurl;
        this.articleUrl =articalurl;
    }

    //返回一个列表
    public static List<EnvironmentI> getAllEnvironments(){
        List<EnvironmentI> environments = new ArrayList<EnvironmentI>();
        environments.add(new EnvironmentI("大创项目介绍 | 校园内垃圾分类回收问题研究","本项目将在大创展正式和大家见面，欢迎大家持续关注我们！" , "http://m.qpic.cn/psc?/V51o6AfO138S3m3lNdc10hOSZE4VGVwk/45NBuzDIW489QBoVep5mcWZNvbByeauNY*34l.pm7A1WKT3axiRTs3gl95GzJtq1U.lEo2ZMUlv8i87oVY*YrJHPb88Ipv43WcPAMn7wQZQ!/b&bo=2wOaAdsDmgEBGT4!&rf=viewer_4&t=5","https://mp.weixin.qq.com/s/w-R3Ee-JThB86g9qXRMfDw"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("http://123.60.15.193//rubbish/index.php/Home/Article/article_all");
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
                    Log.d(response.toString(), "run: ");
                    try {
                        JSONObject jsonObject1 = new JSONObject(response.toString());
                        JSONArray jsonArray=jsonObject1.getJSONArray("data");
                        for (int i = jsonArray.length()-1; i >0; i--) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String title = jsonObject.getString("title");
                            String context = jsonObject.getString("context");

                            Log.d("MainActivity", "标题名称：" + title);
                            Log.d("MainActivity", "文章介绍：" + context);

                            String pic_url = jsonObject.getString("pic_url");
                            String articleUrl = jsonObject.getString("url");

                            Log.d("MainActivity", "图片url：" + pic_url);
                            Log.d("MainActivity", "文章url：" + articleUrl);

                            environments.add(new EnvironmentI(title, context, pic_url,articleUrl));
                        }


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

        //environments.add(new EnvironmentI("大创项目介绍 | 校园内垃圾分类回收问题研究","本项目将在大创展正式和大家见面，欢迎大家持续关注我们！" , "http://m.qpic.cn/psc?/V51o6AfO138S3m3lNdc10hOSZE4VGVwk/45NBuzDIW489QBoVep5mcWZNvbByeauNY*34l.pm7A1WKT3axiRTs3gl95GzJtq1U.lEo2ZMUlv8i87oVY*YrJHPb88Ipv43WcPAMn7wQZQ!/b&bo=2wOaAdsDmgEBGT4!&rf=viewer_4&t=5","https://mp.weixin.qq.com/s/w-R3Ee-JThB86g9qXRMfDw"));
        //environments.add(new EnvironmentI("狐妖小红娘", R.drawable.huyao, "《狐妖小红娘》是根据同名漫画改编的“玄幻搞笑纯情虐恋”故事，讲述了以红娘为职业的狐妖在为前世恋人牵红线过程当中发生的一系列有趣、神秘的故事，这里不仅有人妖之恋的不渝，还能见识各种角度的flag。初次食用一定要熬过前五集。"));
        //environments.add(new EnvironmentI("罗小黑战记", R.drawable.luoxiaohei, "《罗小黑战记》是中国大陆独立动画制作人MTJJ及其工作室制作的一部FLASH动画片，主要讲述的是猫妖盗取天明珠被谛听发现，被打回原形重伤而逃，流落街头之时被萌妹子罗小白带回了家，起名罗小黑由此发生的种种离奇故事。动漫力争两月更新一集，一集七八分钟，处处是彩蛋，目前由于筹备大电影而停更，是我与作者比命长系列之典范。"));

        return environments;

    }
    public String getTitle(){
        return title;
    }
    public void setName(String title){
        this.title=title;
    }
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picurl) {
        this.picUrl = picurl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }


}
