package com.example.workoutroutine.recyclerviewadapter;

public class Select_workoutItem {

    String name;
    int image;
    int clicked;

    public Select_workoutItem(String name, int image){
        super();
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
