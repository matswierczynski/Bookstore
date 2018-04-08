package com.example.matik.bookstore;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity {
    private static final int DEFAULT_CATEGORY_ID = 1;
    private RecyclerView mRecyclerView;
    private BookAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        int categoryId = getIntent().getIntExtra(Intent.EXTRA_TEXT,
                        DEFAULT_CATEGORY_ID);
        List<Book> books = AppDatabase.getInstance(this).bookDAO().
                                        loadAllByCategories(categoryId);
        List<String>  bookNames = new ArrayList<>();
        List<Integer> bookAutors = new ArrayList<>();
        List<Double>  bookPrices = new ArrayList<>();

        for (Book book : books){
            bookNames.add(book.getName());
            bookAutors.add(book.getAuthorID());
            bookPrices.add(book.getPrice());
        }
        mRecyclerView = findViewById(R.id.books_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new BookAdapter(this, bookNames, bookAutors, bookPrices);
        mRecyclerView.setAdapter(mAdapter);
        //mAdapter.setOnClick(this);

    }
}
