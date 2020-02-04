package com.example.fragmentsharedataexample.ui.view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewViewModel extends ViewModel { //ViewModel sınıfından miras alınıyor
    // TODO: Implement the ViewModel

    private MutableLiveData<String> mText; //Verinin tutulacağı değişkenin referansı oluşturuluyor



    public ViewViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("Please update me !"); // ilk değeri atanıyor
    }

    //get ve set metotları

    public MutableLiveData<String> getMessage(){
        return mText;
    }

    public void setMessage(String message){
        mText.setValue(message);
    }

}
