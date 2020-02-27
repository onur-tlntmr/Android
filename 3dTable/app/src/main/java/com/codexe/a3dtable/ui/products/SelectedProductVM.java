package com.codexe.a3dtable.ui.products;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codexe.a3dtable.model.Product;

public class SelectedProductVM extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<Product> product;

    public SelectedProductVM() {
        product = new MutableLiveData<>();
    }

    public void setProduct(Product data) {
        product.setValue(data);
    }

    public MutableLiveData<Product> getProduct() {
        return product;
    }

}
