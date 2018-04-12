package com.example.matik.bookstore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity
                            implements OnItemClicked{
    private static final int DEFAULT_CATEGORY_ID = 1;
    private RecyclerView mRecyclerView;
    private BookAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Book> books;

    public static void startBookPageActivity(Context context, int bookId) {
        Intent starter = new Intent(context, BookPageActivity.class);
        starter.putExtra(Intent.EXTRA_TEXT, bookId );
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        books = getAllBooksByCategory(getCategoryIdFromExtra());
        initializeRecyclerView();

    }

    private Integer getCategoryIdFromExtra(){
        return getIntent().getIntExtra(Intent.EXTRA_TEXT,
                DEFAULT_CATEGORY_ID);
    }

    private List<Book> getAllBooksByCategory(Integer categoryId){
        return AppDatabase.getInstance(this).bookDAO().
                loadAllByCategories(categoryId);
    }


    private void initializeRecyclerView(){
        List<String>  bookNames = new ArrayList<>();
        List<Integer> bookAutors = new ArrayList<>();
        List<Double>  bookPrices = new ArrayList<>();
        List<Integer> bookIds    = new ArrayList<>();

        for (Book book : books){
            bookNames.add(book.getName());
            bookAutors.add(book.getAuthorID());
            bookPrices.add(book.getPrice());
            bookIds.add(book.get_id());
        }
        mRecyclerView = findViewById(R.id.books_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new BookAdapter(this, bookIds,
                bookNames, bookAutors, bookPrices);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClick(this);
    }

    @Override
    public void onItemClick(int position) {
        int bookId = books.get(position).get_id();
        startBookPageActivity(this, bookId);
    }
}
