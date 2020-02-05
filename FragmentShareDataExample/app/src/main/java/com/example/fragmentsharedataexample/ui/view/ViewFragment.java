package com.example.fragmentsharedataexample.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fragmentsharedataexample.R;

public class ViewFragment extends Fragment { //Verinin gösterildiği fragment sınıfı

    private ViewViewModel mViewModel;

    private View root;

    private TextView txtMessage;

    public static ViewFragment newInstance() {
        return new ViewFragment();
    }

    private void init() {
        txtMessage = root.findViewById(R.id.txtMessage);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.view_fragment, container, false);

        init();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(getActivity()).get(ViewViewModel.class); //ModelView Nesnesi oluşuturuluyor

        mViewModel.getMessage().observe(getViewLifecycleOwner(), new Observer<String>() { // Her mesaj güncellendiğinde textview'i yeni değeri ile  değiştiriyoruz
            @Override
            public void onChanged(String s) {
                txtMessage.setText(s);
            }
        });

    }

}
