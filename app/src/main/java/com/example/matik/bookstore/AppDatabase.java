package com.example.matik.bookstore;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.huma.room_for_asset.RoomAsset;


@Database(entities = {Author.class, Book.class, Category.class}, version = AppDatabase.DB_VERSION)
abstract class AppDatabase extends RoomDatabase {
    static final int DB_VERSION = 2;
    private static AppDatabase appDatabase=null;

    static AppDatabase getInstance(Context context){
        if (appDatabase == null) {
            appDatabase = RoomAsset
                    .databaseBuilder(context,
                            AppDatabase.class, "Bookstore.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }

    abstract AuthorDAO   authorDAO();
    abstract BookDAO     bookDAO();
    abstract CategoryDAO categoryDAO();

}
