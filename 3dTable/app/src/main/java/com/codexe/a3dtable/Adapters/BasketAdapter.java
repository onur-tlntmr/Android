package com.codexe.a3dtable.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.codexe.a3dtable.R;
import com.codexe.a3dtable.model.Product;

import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> { //Sepet icin gerekli adapter sinif

    private Context context;
    private ArrayList<Product> products;
    private OnBasketListener onBasketListener; // tiklanma dinleyicisi

    public BasketAdapter(Context context, ArrayList<Product> products, OnBasketListener onBasketListener) {
        this.context = context;
        this.products = products;
        this.onBasketListener = onBasketListener;
    }

    //Fragment ile gevsek bag olusturan ve tiklanan nesnenin indexini gonderebilmek icin kullanılan interface
    public interface OnBasketListener {
        void basketOnclick(int position, int id);
    }

    public static class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView cv;
        public TextView txtName;
        public TextView txtPrice;
        private Button btnDelete;
        private OnBasketListener onBasketListener;

        public ProductHolder(@NonNull View itemView, OnBasketListener onBasketListener) {
            super(itemView);

            cv = itemView.findViewById(R.id.fr_basket_rv);
            txtName = itemView.findViewById(R.id.fr_basket_txt_name);
            txtPrice = itemView.findViewById(R.id.fr_basket_txt_price);
            btnDelete = itemView.findViewById(R.id.fr_basket_btn_delete);

            this.onBasketListener = onBasketListener;
            itemView.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBasketListener.basketOnclick(getAdapterPosition(), v.getId()); // tiklanan urunun numarasini listener referansina tasiyoruz
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ProductHolder(li.inflate(R.layout.item_design_basket, parent, false), onBasketListener); //  Holder'daki listener'i bir ust sinifa tasiyoruz
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product product = products.get(position);
        ProductHolder container = (ProductHolder) holder;
        container.txtName.setText(product.getName());
        container.txtPrice.setText(product.getPrice() + " ₺");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
