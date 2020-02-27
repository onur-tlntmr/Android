package com.codexe.a3dtable.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codexe.a3dtable.Adapters.ProductAdapter;
import com.codexe.a3dtable.R;
import com.codexe.a3dtable.model.Product;

import java.util.ArrayList;

public class ProductFragment extends Fragment implements ProductAdapter.OnProductListener {

    private SelectedProductVM mViewModel;

    private RecyclerView recyclerView;
    private ArrayList<Product> products;
    private ProductAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private NavController navController;


    private void init(View root) {

        products = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            products.add(new Product("Tablo " + i, 120, 34));
        }

        adapter = new ProductAdapter(getContext(), products, this);

        recyclerView = root.findViewById(R.id.lst);

        recyclerView.setAdapter(adapter);

        layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.scrollToPosition(1);
        recyclerView.setHasFixedSize(true);


    }

    public static ProductFragment newInstance() {
        return new ProductFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_products, container, false);
        init(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(SelectedProductVM.class);
    }

    @Override
    public void onProductClick(int position) {

        mViewModel.setProduct(products.get(position));
        navController.navigate(R.id.action_nav_products_to_nav_productDetail);
    }
}
