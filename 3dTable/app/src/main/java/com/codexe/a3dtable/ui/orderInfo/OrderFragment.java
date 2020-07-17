package com.codexe.a3dtable.ui.orderInfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.codexe.a3dtable.R;
import com.codexe.a3dtable.model.User;
import com.codexe.a3dtable.ui.basket.BasketModelView;
import com.codexe.a3dtable.ui.login.LoginViewModel;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */

public class OrderFragment extends Fragment { //Siparisleri gosteren fragment
    // Gerekli Veriler Doldurulacak
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private CheckBox checkBox;
    private Button btn;
    private long total_price = 0;
    private TextView txt_price, txt_order_number, txt_user_name, txt_phone, txt_address;
    private NavController navController; //Fragmentlar arasinda gecis yapmak icin gerekli


    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        total_price = OrderFragmentArgs.fromBundle(getArguments()).getTotalPrice(); // Toplam Fiyat bir onceki fragment'an alındı
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_order, container, false);
        init(root); //gui componentleri initialize edildi

        displayInfo(total_price, 624889); //Toplam fiyat gosterildi, servis olmadigi icin rastgele bir siparis numarsi verildi

        registerHandlers();  // eventler tanimlandi

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

    }

    private void init(View root) { //Componentleri initialize eden method
        checkBox = root.findViewById(R.id.fr_order_checkbox);
        btn = root.findViewById(R.id.fr_order_btn);
        txt_price = root.findViewById(R.id.fr_order_price);
        txt_user_name = root.findViewById(R.id.fr_order_txt_user_name);
        txt_order_number = root.findViewById(R.id.fr_order_txt_order_number);
        txt_phone = root.findViewById(R.id.fr_order_txt_phone);
        txt_address = root.findViewById(R.id.fr_order_txt_address);

    }


    private void displayInfo(long total_price, long order_number) { // Siparis bilgisini gosteren method


        LoginViewModel loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        User user = loginViewModel.getUser();

        txt_order_number.setText(String.format("Sipariş Numarası: %d", order_number));
        txt_user_name.setText(String.format("Alıcı %s %s", user.getName(), user.getSur_name()));
        txt_phone.setText(String.format("Telefon %s", user.getPhone_number()));
        txt_address.setText(String.format("Adres: %s", user.getAddress().getFullAddress()));
        txt_price.setText(String.format("Tutar %d₺", total_price));
    }


    private void checkOnChange() { // checbox'un change eventi
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    btn.setEnabled(true);
                else
                    btn.setEnabled(false);
            }
        });
    }


    private void btnClick() { //siparis onaynaldiginda tetiklenen method
        btn.setOnClickListener(new View.OnClickListener() {
            AlertDialog dialog = null;
            FragmentActivity activity = requireActivity();


            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(activity); //Dialog olusturmak icin gerekli

                View view = activity.getLayoutInflater().inflate(R.layout.message_success, null); // xml dosyasi view e donusturuluyor


                // Boyutu ayarlamak ekran bilgilerini aliniyor
                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);



                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;


                //ekranin etrafinda bosluklar kalmasi icin olculeri cok yakin bir degere donusturuyoruz
                final float numb = 0.9999f;

                width *= numb;
                height *= numb;

                //ana sayfaya git butonu
                Button btnGotoProducts = (Button) view.findViewById(R.id.msg_btn_close);

                btnGotoProducts.setOnClickListener(new View.OnClickListener() { //tiklanma eventi
                    @Override
                    public void onClick(View v) {

                        if (dialog != null) { // dialog olusturulmus ise
                            dialog.dismiss(); //dialog'u kapat
                            navController.navigate(R.id.action_nav_order_to_nav_products); //anasayfaya git
                        }
                    }
                });

                builder.setView(view); //dialog'ta ozel view'i kullanmasi icin gerekli

                dialog = builder.create();

                dialog.show();
                dialog.getWindow().setLayout(width, height); // ozel olculerimiz ayarlandi


                //Sepetteki elemanlar temizlendi
                BasketModelView basketModelView = new ViewModelProvider(activity).get(BasketModelView.class);
                basketModelView.clear();
            }
        });
    }

    //Eventler kaydedildi
    private void registerHandlers() {
        checkOnChange();
        btnClick();
    }


}