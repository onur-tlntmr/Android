package com.codexe.a3dtable.ui.productDetail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.codexe.a3dtable.R;
import com.codexe.a3dtable.model.Product;
import com.codexe.a3dtable.ui.basket.BasketModelView;
import com.codexe.a3dtable.ui.products.SelectedProductVM;
import com.google.android.material.snackbar.Snackbar;


public class ProductDetailFragment extends Fragment {


    private TextView txtName, txtPrice, txtQuantitiy;
    private Button btnAddToBasket;
    private NavController navController;
    private SelectedProductVM selectedProductVM;

    private void init(View v) {
        txtName = v.findViewById(R.id.txtName);

        txtPrice = v.findViewById(R.id.txtPrice);

        txtQuantitiy = v.findViewById(R.id.txtQuantity);
        btnAddToBasket = v.findViewById(R.id.btnAddToBasket);
    }


    public static ProductDetailFragment newInstance() {
        return new ProductDetailFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_product_detail, container, false);

        init(root);
        registerHandlers();
        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // TODO: Use the ViewModel

        selectedProductVM = new ViewModelProvider(getActivity()).get(SelectedProductVM.class);

        selectedProductVM.getProduct().observe(getViewLifecycleOwner(), new Observer<Product>() {
            @Override
            public void onChanged(Product p) {

                txtName.setText("Ürün Adı: " + p.getName());
                txtPrice.setText(p.getPrice() + " ₺");
                txtQuantitiy.setText("Adet: " + p.getQuantity());

            }
        });


    }


    private void btnAddToBasket_Click() {
        btnAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//            View view = getActivity().findViewById(R.id.contextView);


                Log.v("ProductDetailFragment", "Click");

                BasketModelView basketModelView = new ViewModelProvider(requireActivity()).get(BasketModelView.class);
                SelectedProductVM productVM = new ViewModelProvider(requireActivity()).get(SelectedProductVM.class);

                Product p = productVM.getProduct().getValue();

                basketModelView.addProduct(p);

                Snackbar.make(v, "Sepete Eklendi", Snackbar.LENGTH_LONG).setAction("Sepete Git", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        navController.navigate(R.id.action_nav_productDetail_to_basketFragment);
                    }
                }).show();

            }
        });
    }


    private void registerHandlers() {
        btnAddToBasket_Click();
    }

}
