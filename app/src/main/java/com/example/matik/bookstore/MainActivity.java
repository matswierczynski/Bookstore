package com.example.matik.bookstore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.huma.room_for_asset.RoomAsset;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static final int DB_VERSION = 2;
    static  AppDatabase db;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDatabase();
        initializeRecyclerView();
    }

    private void initializeDatabase(){
        db = RoomAsset
                .databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "Bookstore.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    private void initializeRecyclerView(){
        List<Category> categories = db.categoryDAO().getAll();
        mRecyclerView = findViewById(R.id.categories_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CategoryRecView(this, categories);
        mRecyclerView.setAdapter(mAdapter);
    }


}
