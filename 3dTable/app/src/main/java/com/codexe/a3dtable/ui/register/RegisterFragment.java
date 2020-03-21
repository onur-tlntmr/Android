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
    private int validation_count = 0; //gecerli verilerin sayisinin tutuldugu sayac
    private EditText edt_name, edt_surname, edt_mail, edt_passwd, edt_passwd_again, edt_phone, edt_address;
    private FragmentTransaction transaction;
    private String error_message;

    class EditTextWatch implements TextWatcher { //EditText'lerin textChangeListener'ı

        /* Bu sinif tum editTextlere veri girildiginde kayit ol butonunu aktiflestirmeye yarar
         */
        protected Boolean isIncrement = false; // O anki editText sayaci artirip artirmadigini durumu tutar

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { // text verisi degistiginde tetiklenen metot
            if (s.length() > 0 && !isIncrement) { // eger veri bos degilse ve daha once artirma yapilmamis ise
                validation_count++;  // sayaci bir artirir ve artirildi bayragini true yapar
                isIncrement = true;

            } else if ((TextUtils.isEmpty(s) || s == null) && isIncrement) { // daha once artirilmis ve icindeki veri silinmis ise
                validation_count--; // sayaci bir azaltir ve durumu artirilmamis olarak gunceller
                isIncrement = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) { // degisimden sonra sayacın istenilen degerine gelip gelmedigini kontrol eder

            if (validation_count == editTexts.size()) { // eger tumu doldurulmus ise
                btn_register.setEnabled(true); // butonu aktif et
            } else {//dolmamis ise
                btn_register.setEnabled(false); //butonu  pasif yap
            }
        }
    }

    //Sifre islemlerinin eventleri farkli oldugundan mutevellit, onlarin eventleri de farkli olarak handle edilmektedir
    class PasswordTextWatch extends EditTextWatch { //Sifre girisi yapilirken handle edilen event

        CharSequence passwd_again = edt_passwd_again.getText(); // tekrardaki sifre bilgisi aliniyor

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            super.onTextChanged(s, start, before, count); // Parent sinifindaki gibi veri girisi yapildiginda ayni islemler oluyor

            if (!TextUtils.equals(passwd_again, s)) { //sadece tekrar alanindaki degerler ayni degilse hata mesaji veriyor
                edt_passwd_again.setError(error_message);
            }

        }

        //!afterTextChange metotdu miras olarak alinmistir
    }

    class PasswordAgainTextWatch extends EditTextWatch { // Sifre tekrarinin eventi handle ediliyor

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            CharSequence passwd = edt_passwd.getText(); // girilen sifre bilgisi aliniyor

            if ((TextUtils.equals(passwd, s) && !isIncrement && s.length() > 0)) { // girilen sifreler ayni ise ve daha once artirilmamis ve bos degilse
                edt_passwd_again.setError(null);//hata mesaji temizleniyor
                isIncrement = true; // artirilmis olarak isaretleniyor
                validation_count++; // dogru veri girisi bir artıyor
            } else if (((!TextUtils.equals(passwd, s) && isIncrement)) || s.length() == 0) { // girilen sifreler farkli ve artirilma olmus ise veyahut boyutu bos ise
                edt_passwd_again.setError(null); //bazen hata mesajini basmiyor o yuzden tekrar temizleniyor
                edt_passwd_again.setError(error_message); // hata mesaji basiliyor
                isIncrement = false; // artirilmamis olarak isaretleniyor
                validation_count--; // girilen veri sayisi degiskeni bir azaltiliyor

            }
        }

        //!afterTextChange metotdu miras olarak alinmistir

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
        edt_passwd_again = root.findViewById(R.id.fr_register_edt_passwd_again);
        edt_phone = root.findViewById(R.id.fr_register_edt_phone_number);
        edt_address = root.findViewById(R.id.fr_register_edt_address);

        editTexts = getChildEditTexts(); //edtlerin eventleri toplu olarak set etmek icin gerekli

        error_message = getString(R.string.paswwd_not_same);
    }


    public static RegisterFragment newInstance() {
        return new RegisterFragment();
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

        for (EditText e : editTexts) { //eventlerin yerlestirmesi icin editTextler tek tek collectiondan aliniyor
            if (e.getInputType() != edt_passwd.getInputType()) {//eger editText sifre inputType'ni tasiyor ise onlara eklenmiyor
                e.addTextChangedListener(new EditTextWatch());
            }
        }
        //Sifrelerin eventleri farkli oldugu icin onlarinki ayri olarak ekleniyor
        edt_passwd.addTextChangedListener(new PasswordTextWatch());
        edt_passwd_again.addTextChangedListener(new PasswordAgainTextWatch());
    }

    private User createUser() { //editTextlerdeki verilerden yeni bir user olusturan metot

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

    private void btnRegister_click() {//yeni kullanici olusturulduktan sonra sisteme ekleyip giris ekrarnina yonlendiriyor
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