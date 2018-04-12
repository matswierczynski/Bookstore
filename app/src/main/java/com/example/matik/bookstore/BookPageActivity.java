package com.example.matik.bookstore;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookPageActivity extends AppCompatActivity {
    private static final int DEFAULT_BOOK_ID = 1;

    public static void startCartActivity(Context context) {
        Intent starter = new Intent(context, CartActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_page);
        setViews(getBookIdFromExtra());
        findViewById(R.id.bookAddToCart).
                setOnClickListener(addToCartOnClickListener);
        findViewById(R.id.bookCart).
                setOnClickListener(cartOnClickListener);
    }

    View.OnClickListener addToCartOnClickListener =
            (v)-> {
                CartState.getInstance().addBook(getBookIdFromExtra());
                Toast.makeText(this,
                        getResources().getString(R.string.added_success).toString(),
                        Toast.LENGTH_SHORT).show();
            };

    View.OnClickListener cartOnClickListener =
            (v) -> startCartActivity(getApplicationContext());

    private int getBookIdFromExtra(){
        return getIntent().getIntExtra(Intent.EXTRA_TEXT, DEFAULT_BOOK_ID);
    }

    private void setViews(final int bookId){
       Book clickedBook = AppDatabase.getInstance(this).
               bookDAO().loadById(bookId);
       Author bookAuthor = AppDatabase.getInstance(this).
               authorDAO().loadById(clickedBook.getAuthorID());

        ((ImageView)findViewById(R.id.bookImage)).setImageResource(getResources().getIdentifier(
                "b"+String.valueOf(bookId),
                "drawable", getPackageName()));
        ((TextView) findViewById(R.id.bookTitle)).setText(clickedBook.getName());
        ((TextView) findViewById(R.id.bookAuthor)).
                setText(bookAuthor.getName() +" "+bookAuthor.getLastname());
        ((TextView) findViewById(R.id.bookPrice)).
                setText(String.format("%.2f", clickedBook.getPrice())+"$");
    }



}
