package com.example.matik.bookstore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Map;


public class CartAdapter extends RecyclerView.Adapter
        <CartAdapter.CartViewHolder> {
    private OnItemClicked onClick;
    private OnPlusItemClicked onPlusItemClicked;
    private OnMinusItemClicked onMinusItemClicked;
    private LayoutInflater layoutInflater;
    private Map<Integer, Integer> books;
    private Integer [] booksIds;
    private static Context context;

    CartAdapter(Context context, Map<Integer, Integer> books){
        layoutInflater=LayoutInflater.from(context);
        this.books = books;
        booksIds = books.keySet().toArray(new Integer[books.size()]);
        this.context = context;
    }

    void reload(Map<Integer, Integer> books){
        if (this.books != null){
            this.books.clear();
            this.books.putAll(books);
            booksIds = books.keySet().toArray(new Integer[books.size()]);
        }
        else
            this.books = books;
        notifyDataSetChanged();
    }
    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cart_row, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, final int position) {
        double unitPrice = (AppDatabase.getInstance(context).bookDAO().
                loadById(booksIds[position]).getPrice());
        int quantity = books.get(booksIds[position]);
        int imageId = context.getResources().getIdentifier(
                "b" + String.valueOf(booksIds[position]),
                "drawable", context.getPackageName());
        holder.cartBookImage.setImageResource(imageId);
        holder.cartBookTitle.setText(
                AppDatabase.getInstance(context).bookDAO().
                        loadById(booksIds[position]).getName());
        holder.cartBookUnitPrice.setText(String.format("%.2f", unitPrice) + "$");
        holder.cartBookQuantity.setText("x " + String.valueOf(quantity));
        holder.cartBookFinalPrice.setText(String.format("%.2f", unitPrice * quantity) + "$");
        holder.cartBookImage.setOnClickListener(v -> onClick.onItemClick(position));
        holder.decreaseQuantity.setOnClickListener(
                v -> onMinusItemClicked.onMinusItemClicked(position));
        holder.increaseQuantity.setOnClickListener(
                v -> onPlusItemClicked.onPlusItemClicked(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }
    void setOnPlusItemClicked(OnPlusItemClicked onPlusItemClicked){
        this.onPlusItemClicked = onPlusItemClicked;
    }
    void setOnMinusItemClicked(OnMinusItemClicked onMinusItemClicked){
        this.onMinusItemClicked = onMinusItemClicked;
    }
    static class CartViewHolder extends RecyclerView.ViewHolder{

        private TextView cartBookTitle, cartBookUnitPrice, cartBookQuantity,
                        cartBookFinalPrice;
        private ImageView cartBookImage;
        private ImageButton increaseQuantity, decreaseQuantity;


        CartViewHolder(View itemView) {
            super(itemView);
            cartBookImage = itemView.findViewById(R.id.cartRowBookView);
            cartBookTitle  = itemView.findViewById(R.id.cartBookTitle);
            cartBookUnitPrice = itemView.findViewById(R.id.cartBookSinglePrice);
            cartBookQuantity = itemView.findViewById(R.id.cartBookQuantity);
            cartBookFinalPrice = itemView.findViewById(R.id.cartBookFinalPrice);
            increaseQuantity   = itemView.findViewById(R.id.cartBookIncreaseQuantity);
            decreaseQuantity   = itemView.findViewById(R.id.cartBookDecreaseQuantity);
        }
    }
}
