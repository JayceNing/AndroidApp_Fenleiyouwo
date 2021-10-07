package com.example.fenleiyouwo.ui.me;

public class Items {
    private String name;
    private int imageId;
    private int id;
    public Items(String name,int imageId,int id){
        this.name=name;
        this.imageId=imageId;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public int getId() {
        return id;
    }
}
