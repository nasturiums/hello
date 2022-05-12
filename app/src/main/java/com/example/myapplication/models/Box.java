package com.example.myapplication.models;

public class Box {
    String decrition;
    String image;
    boolean selected;

    public Box(String decrition, String image, boolean selected) {
        this.decrition = decrition;
        this.image = image;
        this.selected = selected;
    }

    public String getDecrition() {
        return decrition;
    }

    public void setDecrition(String decrition) {
        this.decrition = decrition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
