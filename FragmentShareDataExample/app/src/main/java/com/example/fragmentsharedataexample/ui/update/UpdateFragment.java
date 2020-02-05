package com.example.fragmentsharedataexample.ui.update;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fragmentsharedataexample.R;
import com.example.fragmentsharedataexample.ui.view.ViewViewModel;

public class UpdateFragment extends Fragment { // Verinin güncellendiği fragment

    //Gerekli referanslar oluşturuluyor
    private View root;
    private EditText edtMessage;
    private Button btnUpdate;

    private void init() {

        edtMessage = root.findViewById(R.id.edtMessage);
        btnUpdate = root.findViewById(R.id.btnUpdate);
    }

    private void btnUpdate_Click() { //Her butona tıklandiğında EditTextteki değer ile livedatamız güncelleniyor
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ViewViewModel model = new ViewModelProvider(getActivity()).get(ViewViewModel.class);
                model.setMessage(edtMessage.getText().toString());
                Toast.makeText(requireActivity(), "Message has been updated !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static UpdateFragment newInstance() {
        return new UpdateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.update_fragment, container, false);
        init();
        btnUpdate_Click();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
