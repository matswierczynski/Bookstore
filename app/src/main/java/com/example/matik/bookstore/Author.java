package com.example.matik.bookstore;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Author {

    @PrimaryKey
    private int _id;

    @ColumnInfo
    @NonNull
    private String name="";

    @ColumnInfo(name="lastName")
    private String lastname;


    public int get_id() {
        return _id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
