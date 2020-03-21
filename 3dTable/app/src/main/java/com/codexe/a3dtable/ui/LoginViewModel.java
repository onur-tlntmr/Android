package com.codexe.a3dtable.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codexe.a3dtable.model.User;

public class LoginViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<User> mLiveData;


    public LoginViewModel() {
        mLiveData = new MutableLiveData<>();
    }

    public void setUser(User user) {
        mLiveData.setValue(user);
    }


    public User getUser() {
        return mLiveData.getValue();
    }


    public MutableLiveData<User> getLiveData() {
        return mLiveData;
    }

}
