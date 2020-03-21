package com.codexe.a3dtable.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

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
    private SearchView searchView;

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

        setHasOptionsMenu(true);


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
        navController = Navigation.findNavController(view); //Bir sonraki fragment'a gecmek icin gerekli
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(SelectedProductVM.class); //Secili olan urunu tutmasi icin gerekli viewmodel
    }


    @Override
    public void onProductClick(int position) { //tiklanan urunun indexini alir
        mViewModel.setProduct(products.get(position)); // tiklanan urunu viewmodel'e yerlestirir
        navController.navigate(R.id.action_nav_products_to_nav_productDetail); // urun detayina gider
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) { //
        MenuItem item = menu.findItem(R.id.action_search); //searchview in eventi yerlestirilir
        searchView = (SearchView) item.getActionView();
        srch_OnQueryTextListener();

    }

    private void srch_OnQueryTextListener() { //searchview'e yazi eklenince tetiklenen listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { //
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                String input = newText.toLowerCase(); // girilen text hepsi kucukhale donusturulur
                ArrayList<Product> newList = new ArrayList<>(); // yeni ürünler icin yeni list yapilir

                for (Product product : products) { // tum urunlerden her bir urununun adi girilen veri ile karsilastirilir
                    if (product.getName().toLowerCase().contains(input)) { // varsa
                        newList.add(product); // yeni listeye eklenilir
                    }
                }
                products = newList; // yeni liste guncellenir ki indexler karismasin diye
                adapter.updateList(newList); //adapter sinif guncellenir

                return false;
            }
        });
    }





}
