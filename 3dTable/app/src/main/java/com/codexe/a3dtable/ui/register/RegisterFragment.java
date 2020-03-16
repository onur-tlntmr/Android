package com.codexe.a3dtable.ui.register;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.codexe.a3dtable.R;
import com.codexe.a3dtable.TestUtils.SampleDB;
import com.codexe.a3dtable.model.Address;
import com.codexe.a3dtable.model.User;
import com.codexe.a3dtable.ui.login.LoginFragment;

import java.util.ArrayList;


public class RegisterFragment extends Fragment { //Yeni kayit yapilmasini saglayan fragment

    private Spinner spn_city;
    private Button btn_register;
    private ArrayAdapter<CharSequence> adapter;
    private ArrayList<EditText> editTexts;
    private GridLayout pnl;
    private int entry_data_edit;
    private EditText edt_name, edt_surname, edt_mail, edt_passwd, edt_phone, edt_address;
    private FragmentTransaction transaction;

    class EditTextWatch implements TextWatcher { //EditText'lerin textChangeListener'ı

        /* Bu sinif tum editTextlere veri girildiginde kayit ol butonunu aktiflestirmeye yarar
         */
        private Boolean isIncrement = false; // O anki editText sayaci artirip artirmadigini durumu tutar

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { // text verisi degistiginde tetiklenen metot
            if (s.length() > 0 && !isIncrement) { // eger veri bos degilse ve daha once artirma yapilmamis ise
                entry_data_edit++;  // sayaci bir artirir ve artirildi bayragini true yapar
                isIncrement = true;
            } else if ((TextUtils.isEmpty(s) || s == null) && isIncrement) { // daha once artirilmis ve icindeki veri silinmis ise
                entry_data_edit--; // sayaci bir azaltir ve durumu artirilmamis olarak gunceller
                isIncrement = false;
            }

        }

        @Override
        public void afterTextChanged(Editable s) { // degisimden sonra sayacın istenilen degerine gelip gelmedigini kontrol eder

            if (entry_data_edit == editTexts.size()) { // eger tumu doldurulmus ise
                btn_register.setEnabled(true); // butonu aktif et
            } else {//dolmamis ise
                btn_register.setEnabled(false); //butonu  pasif yap
            }
        }
    }

    private void init(View root) {
        transaction = getActivity().getSupportFragmentManager().beginTransaction(); //fragment gecisleri icin
        adapter = ArrayAdapter.createFromResource(requireContext(), R.array.cities, R.layout.support_simple_spinner_dropdown_item); // spn adaptoru

        pnl = root.findViewById(R.id.fr_register_grd_pnl);
        btn_register = root.findViewById(R.id.fr_register_btnRegister);
        spn_city = root.findViewById(R.id.fr_register_spn_city);

        edt_name = root.findViewById(R.id.fr_register_edt_name);
        edt_surname = root.findViewById(R.id.fr_register_edt_surname);
        edt_mail = root.findViewById(R.id.fr_register_edt_mail);
        edt_passwd = root.findViewById(R.id.fr_register_edt_passwd);
        edt_phone = root.findViewById(R.id.fr_register_edt_phone_number);
        edt_address = root.findViewById(R.id.fr_register_edt_address);

        editTexts = getChildEditTexts(); //edtlerin eventleri toplu olarak set etmek icin gerekli
    }


    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_register, container, false);
        init(root);
        spn_city.setAdapter(adapter);
        registerHandlers();

        return root;
    }


    private ArrayList<EditText> getChildEditTexts() { // Tum editTextleri liste halinde veren metot
        int count = pnl.getChildCount();

        ArrayList<EditText> editTexts = new ArrayList<>();
        View v;

        for (int i = 0; i < count; i++) {
            v = pnl.getChildAt(i);
            if (v instanceof EditText)
                editTexts.add((EditText) v);
        }

        return editTexts;
    }

    private void editText_textChange() {

        for (EditText e : editTexts) {
            e.addTextChangedListener(new EditTextWatch());
        }
    }


    private User createUser() { //editTextlerdeki verilerden yeni bir user olusturur
        User u = new User();

        u.setUser_name(edt_name.getText().toString());
        u.setPassword(edt_passwd.getText().toString());
        u.setMail(edt_mail.getText().toString());
        Address address = new Address();
        address.setCity(spn_city.getSelectedItem().toString());
        address.setDetails(edt_address.getText().toString());
        u.setAddress(address);

        return u;
    }


    private void btnRegister_click() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SampleDB sampleDB = SampleDB.getInstance();
                sampleDB.insertUser(createUser());
                transaction.replace(R.id.container, LoginFragment.newInstance()).commitNow();

            }
        });
    }

    private void registerHandlers() {
        editText_textChange();
        btnRegister_click();
    }

}
