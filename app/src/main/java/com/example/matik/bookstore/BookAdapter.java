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
    private OnItemClicked onClick;
    private LayoutInflater layoutInflater;
    private Context context;
    List<Integer> bookIds;
    List<String> bookNames;
    List<Integer> bookAutors;
    List<Double>  bookPrices;

    public BookAdapter(Context context, List<Integer> bookIds, List<String> bookNames,
                       List<Integer> bookAutors, List<Double> bookPrices) {
        layoutInflater=LayoutInflater.from(context);
        this.bookIds   =bookIds;
        this.bookNames = bookNames;
        this.bookAutors = bookAutors;
        this.bookPrices = bookPrices;
        this.context = context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.book_row, parent, false);
        BookViewHolder holder = new BookViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, final int position) {
        int imageId = context.getResources().getIdentifier(
                "b"+String.valueOf(bookIds.get(position)),
                "drawable", context.getPackageName());
        holder.bookName.setText(bookNames.get(position));
        holder.bookPrice.setText(String.format("%.2f", bookPrices.get(position))+"$");
        holder.bookImage.setImageResource(imageId);
        holder.itemView.setOnClickListener((v) ->onClick.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return bookNames.size();
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
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
