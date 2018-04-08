package com.example.matik.bookstore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private LayoutInflater layoutInflater;
    List<String> bookNames;
    List<Integer> bookAutors;
    List<Double>  bookPrices;

    public BookAdapter(Context context, List<String> bookNames,
                       List<Integer> bookAutors, List<Double> bookPrices) {
        layoutInflater=LayoutInflater.from(context);
        this.bookNames = bookNames;
        this.bookAutors = bookAutors;
        this.bookPrices = bookPrices;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.book_row, parent, false);
        BookViewHolder holder = new BookViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, final int position) {
        holder.bookName.setText(bookNames.get(position));
        holder.bookPrice.setText(bookPrices.get(position).toString());
        holder.bookImage.setImageResource(R.drawable.ic_launcher_background);
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return bookNames.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder{
        private TextView bookName, bookPrice;
        private ImageView bookImage;

        public BookViewHolder(View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.singleBookImage);
            bookPrice = itemView.findViewById(R.id.singleBookPrice);
            bookName  = itemView.findViewById(R.id.singleBookTitle);
        }
    }
}
