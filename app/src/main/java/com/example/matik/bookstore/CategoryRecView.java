package com.example.matik.bookstore;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



class CategoryRecView extends RecyclerView.Adapter
        <CategoryRecView.CategoryViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Category> categories;

    CategoryRecView(Context context, List<Category> categories){
        layoutInflater=LayoutInflater.from(context);
        this.categories = categories;

    }
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.category_row, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {

        holder.categoryName.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView categoryName;

        CategoryViewHolder(View itemView) {
            super(itemView);
            categoryName  = itemView.findViewById(R.id.category_single_row_name);

        }
    }
}
