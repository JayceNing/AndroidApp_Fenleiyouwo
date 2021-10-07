package com.example.fenleiyouwo.ui.shop;

public class Item {
    private String name;

    private int imageId;

    private String price;
    private int num;

    public Item(String name, int imageId,String price,int num) {
        this.name = name;
        this.imageId = imageId;
        this.price=price;
        this.num=num;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getPrice(){ return price;}
    public int getNum(){return num;}
}
