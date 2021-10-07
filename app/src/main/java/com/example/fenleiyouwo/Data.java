package com.example.fenleiyouwo;

import android.app.Application;

public class Data extends Application{
    private String id;
    private String username;
    private String phone;
    private String face_url;
    private String water;
    private String fertilizer;
    private String tree_progress;

    public String getid(){
        return this.id;
    }
    public void setid(String c){
        this.id= c;
    }
    public String getusername(){
        return this.username;
    }
    public void setusername(String c){
        this.username= c;
    }
    public String getphone(){
        return this.phone;
    }
    public void setphoneN(String c){
        this.phone= c;
    }
    public String getface_url(){
        return this.face_url;
    }
    public void setface_url(String c){
        this.face_url= c;
    }
    public String getwater(){
        return this.water;
    }
    public void setwaterN(String c){
        this.water= c;
    }
    public String getfertilizer(){
        return this.fertilizer;
    }
    public void setfertilizer(String c){
        this.fertilizer= c;
    }
    public String gettree_progress(){
        return this.tree_progress;
    }
    public void settree_progress(String c){
        this.tree_progress= c;
    }

    @Override
    public void onCreate(){
        //b = "hello";
        super.onCreate();
    }
}
