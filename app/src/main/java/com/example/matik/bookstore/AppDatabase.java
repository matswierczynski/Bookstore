package com.example.matik.bookstore;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {Author.class, Book.class, Category.class}, version = MainActivity.DB_VERSION)
abstract class AppDatabase extends RoomDatabase {


    abstract AuthorDAO   authorDAO();
    abstract BookDAO     bookDAO();
    abstract CategoryDAO categoryDAO();

}
