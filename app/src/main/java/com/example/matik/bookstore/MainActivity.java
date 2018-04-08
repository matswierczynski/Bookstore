package com.example.matik.bookstore;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;


public class MainActivity extends AppCompatActivity
                            implements CategoryAdapter.OnItemClicked{
    static  AppDatabase db;
    List<Category> categories;
    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static void startMoviesActivity(Context context, int categoryNumber) {
        Intent starter = new Intent(context, BooksActivity.class);
        starter.putExtra(Intent.EXTRA_TEXT, categoryNumber);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDatabase();
        initializeRecyclerView();
    }

    private void initializeDatabase(){
        db = AppDatabase.getInstance(this);
    }

    private void initializeRecyclerView(){
        categories = db.categoryDAO().getAll();
        mRecyclerView = findViewById(R.id.categories_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CategoryAdapter(this, categories);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClick(this);
    }


    @Override
    public void onItemClick(int position) {
        int category = categories.get(position).get_id();
        startMoviesActivity(this, category);
    }
}
