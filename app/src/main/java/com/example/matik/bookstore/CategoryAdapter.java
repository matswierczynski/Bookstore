package com.example.matik.bookstore;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;



class CategoryAdapter extends RecyclerView.Adapter
        <CategoryAdapter.CategoryViewHolder> {

    private OnItemClicked onClick;
    private static final int EVEN_NUMBERS_DIVIDER =2;
    private LayoutInflater layoutInflater;
    private List<Category> categories;

    CategoryAdapter(Context context, List<Category> categories){
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
        if (position % EVEN_NUMBERS_DIVIDER == 0)
            holder.mCardView.setCardBackgroundColor(
                    layoutInflater.getContext().getResources().getColor(R.color.cardEven));
        else
            holder.mCardView.setCardBackgroundColor(
                    layoutInflater.getContext().getResources().getColor(R.color.cardOdd));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView categoryName;
        private CardView mCardView;

        CategoryViewHolder(View itemView) {
            super(itemView);
            categoryName  = itemView.findViewById(R.id.singleCategory);
            mCardView     = itemView.findViewById(R.id.category_row);

        }
    }

    public interface OnItemClicked {
        void onItemClick(int position);
    }
}
