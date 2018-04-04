package com.example.matik.bookstore;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by matik on 04.04.2018.
 */
@Entity
class Book {

    @PrimaryKey
    private int _id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="author")
    private int authorID;

    @ColumnInfo(name = "category")
    private int categoryID;

    @ColumnInfo(name = "price")
    private double price;

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public int getAuthorID() {
        return authorID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public double getPrice() {
        return price;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
