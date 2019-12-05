package com.iteso.altomobileproject;

public class Animal {
    private String name;
    private int life;
    private int id;
    private String pictureURL;

    public Animal(String name, int life, int id, String pictureURL){
        this.name=name;
        this.life=life;
        this.id= id;
        this.pictureURL= pictureURL;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", life=" + life +
                ", id=" + id +
                ", pictureURL='" + pictureURL + '\'' +
                '}';
    }
}




