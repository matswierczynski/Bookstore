package com.example.matik.bookstore;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.TreeMap;

public class CartActivity extends AppCompatActivity
                            implements OnItemClicked,
                                        OnPlusItemClicked,
                                        OnMinusItemClicked{

    private RecyclerView mRecyclerView;
    private CartAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Map<Integer, Integer> booksInCart;

    public static void startBookPageActivity(Context context, int bookId) {
        Intent starter = new Intent(context, BookPageActivity.class);
        starter.putExtra(Intent.EXTRA_TEXT, bookId );
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        refreshFromCartState();
        mRecyclerView = findViewById(R.id.cartRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CartAdapter(this, booksInCart);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClick(this);
        mAdapter.setOnMinusItemClicked(this);
        mAdapter.setOnPlusItemClicked(this);
        initializeItemTouchHelper();
    }

    @Override
    public void onItemClick(int position) {
        Integer bookId = booksInCart.keySet().
                toArray(new Integer[booksInCart.size()])[position];
        startBookPageActivity(this, bookId);
    }

    @Override
    public void onMinusItemClicked(int position) {
        int bookId = getBookIdFromAdapterPosition(position);
        int currentQuantity = booksInCart.get(bookId);
        if (currentQuantity > CartState.MINIMAL_NO_OF_ONE_ITEM_IN_CART) {
            CartState.getInstance().decreaseQuantity(bookId);
            refreshFromCartState();
            mAdapter.reload(new TreeMap<>(booksInCart));
        }
    }

    @Override
    public void onPlusItemClicked(int position) {
            int bookId = getBookIdFromAdapterPosition(position);
            CartState.getInstance().addBook(bookId);
            refreshFromCartState();
            mAdapter.reload(new TreeMap<>(booksInCart));
    }

    private Integer getBookIdFromAdapterPosition(Integer adapterPosition){
            return booksInCart.keySet().
                toArray(new Integer[booksInCart.size()])[adapterPosition];
    }

    private void refreshFromCartState(){
        booksInCart = CartState.getInstance().getAll();
        ((TextView)findViewById(R.id.cartSumOfPrices)).setText(
                String.format("%.2f",
                        CartState.getInstance().getAllProductsPrice(this))+ "$");
    }

    public void initializeItemTouchHelper(){
        ItemTouchHelper.SimpleCallback simpleCallback=
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int bookId = getBookIdFromAdapterPosition(
                                viewHolder.getAdapterPosition());
                        CartState.getInstance().removeBook(bookId);
                        refreshFromCartState();
                        mAdapter.reload(new TreeMap<>(booksInCart));
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.removed_success),
                                Toast.LENGTH_SHORT).show();
                    }
                };
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
    itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}