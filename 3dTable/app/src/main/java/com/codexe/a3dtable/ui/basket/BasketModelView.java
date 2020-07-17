package com.codexe.a3dtable.ui.basket;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codexe.a3dtable.model.Product;

import java.util.ArrayList;

public class BasketModelView extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<ArrayList<Product>> products;

    private ArrayList<Product> list;

    public BasketModelView() {
        this.products = new MutableLiveData<>(new ArrayList<Product>());
        list = new ArrayList<>();
    }



    public MutableLiveData<ArrayList<Product>> getProducts() {
        return products;
    }


    public void addProduct(Product p) {

        list.add(p);
        products.setValue(list);
    }

    public void removeProduct(Product p) {
        list.remove(p);
        products.setValue(list);
    }


    public void clear(){
        list.clear();
    }


}
