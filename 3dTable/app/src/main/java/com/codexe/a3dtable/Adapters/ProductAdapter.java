package com.codexe.a3dtable.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.codexe.a3dtable.R;
import com.codexe.a3dtable.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private ArrayList<Product> products;

    private OnProductListener onProductListener;

    public ProductAdapter(Context context, ArrayList<Product> products, OnProductListener onProductListener) {
        this.context = context;
        this.products = products;
        this.onProductListener = onProductListener;
    }

    public interface OnProductListener { // Click ile secilen pozisyonu fragment'a taşımak için kullanılan interface
        void onProductClick(int position); //Clicck eventi ise tiklanınca pozisyon bilgisini tutar
    }

    public static class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener { //click eventindeki bilgiyi


        public CardView cardView;

        public TextView txtName, txtPrice;

        private OnProductListener onProductListener;


        public ProductHolder(@NonNull View itemView, OnProductListener onProductListener) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cvItem);

            txtName = itemView.findViewById(R.id.fr_product_txt_name);

            txtPrice = itemView.findViewById(R.id.fr_product_txt_price);

            this.onProductListener = onProductListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            onProductListener.onProductClick(getAdapterPosition());

        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        final LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return new ProductHolder(li.inflate(R.layout.item_design_product, parent, false), onProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product item = products.get(position);
        ProductHolder container = (ProductHolder) holder;
        container.txtName.setText(item.getName());
        container.txtPrice.setText(item.getPrice() + " ₺");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


}
