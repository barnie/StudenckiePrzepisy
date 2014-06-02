package com.example.studenckieprzepisy.Database;

/**
 * Created by piotr on 21.04.14.
 */
public class Kategoria {
    private int id;
    private String name;
    private String image;

    public Kategoria(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public Kategoria(String name, String image) {
        this.id = 0;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "" + id + " " + name + " " + image;
    }

}
