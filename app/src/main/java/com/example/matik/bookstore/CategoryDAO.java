package com.example.matik.bookstore;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
interface CategoryDAO {
    @Query("SELECT * FROM category")
    List<Category> getAll();

    @Query("SELECT * FROM category WHERE _id IN (:categoryIds)")
    List<Category> loadAllByIds(int[] categoryIds);

    @Query("SELECT * FROM category WHERE name LIKE :name LIMIT 1")
    Category findByName(String name);

    @Insert
    void insertAll(Category category);

    @Delete
    void delete(Category category);
}
