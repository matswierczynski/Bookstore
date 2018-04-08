package com.example.matik.bookstore;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
interface BookDAO {

    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Query("SELECT * FROM book WHERE _id IN (:userIds)")
    List<Book> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM book WHERE category = :categoryId")
    List<Book> loadAllByCategories(int categoryId);

    @Query("SELECT * FROM book WHERE author IN (:authorIds)")
    List<Book> loadAllByAuthors(int[] authorIds);

    @Query("SELECT * FROM book WHERE name LIKE :name LIMIT 1")
    Book findByName(String name);


    @Insert
    void insertAll(Book book);

    @Delete
    void delete(Book book);
}
