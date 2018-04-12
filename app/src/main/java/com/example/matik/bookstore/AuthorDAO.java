package com.example.matik.bookstore;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
interface AuthorDAO {
        @Query("SELECT * FROM author")
        List<Author> getAll();

        @Query("SELECT * FROM author WHERE _id IN (:userIds)")
        List<Author> loadAllByIds(int[] userIds);

        @Query("SELECT * FROM author WHERE _id = :userId")
        Author loadById(int userId);

        @Query("SELECT * FROM author WHERE name LIKE :first AND "
                + "lastName LIKE :last LIMIT 1")
        Author findByName(String first, String last);

        @Insert
        void insertAll(Author author);

        @Delete
        void delete(Author author);
    }
