package com.example.matik.bookstore;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(foreignKeys = {@ForeignKey(entity = Author.class,
        parentColumns = "_id",
        childColumns = "author"),
        @ForeignKey(entity = Category.class,
                parentColumns = "_id",
                childColumns = "category")},
        indices = {@Index(value = {"category", "_id"}, unique = true),
                   @Index(value = {"author", "_id"}, unique = true)}
        )
class Book {

    @PrimaryKey
    private int _id;

    @ColumnInfo
    @NonNull
    private String name;

    @ColumnInfo(name="author")
    @NonNull
    private int authorID;

    @ColumnInfo(name = "category")
    @NonNull
    private int categoryID;

    @ColumnInfo
    @NonNull
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
