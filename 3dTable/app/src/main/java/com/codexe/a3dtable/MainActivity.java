package com.codexe.a3dtable;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.codexe.a3dtable.TestUtils.SampleDB;
import com.codexe.a3dtable.model.Product;
import com.codexe.a3dtable.model.User;
import com.codexe.a3dtable.ui.LoginViewModel;
import com.codexe.a3dtable.ui.basket.BasketModelView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TextView txt_mail, txt_usr_name;
    private NavController navController;
    private NavigationView navigationView;


    private void createSession() {
        Bundle bundle = getIntent().getExtras();
        SampleDB sampleDB = SampleDB.getInstance();
        User user = sampleDB.getUser(bundle.getString("userMail")); // loginControl'den mail adresi ile girilen kullanici bilgileri çekiliyor
        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginViewModel.setUser(user); //girilen kullanici diger fragmentler ile de kullanilabilmesi icin loginviewmodel'e atiliyor !

        View header_view = navigationView.getHeaderView(0);

        txt_usr_name = header_view.findViewById(R.id.nav_txt_name); //gerekli textViewler aliniyor
        txt_mail = header_view.findViewById(R.id.nav_txt_mail);


        loginViewModel.getLiveData().observe(this, new Observer<User>() { // veri her gunclellendiginde ui da guncelleniyor
            @Override
            public void onChanged(User user) { //Kullanici bilgisi guncellendiginde ui'da guncellenecek
                txt_usr_name.setText(String.format("%s %s", user.getName(), user.getSur_name()));
                txt_mail.setText(user.getMail());
            }
        });
    }

    private void setupNavigation() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_products, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    private void setupToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void init() {
        setupToolBar();
        setupNavigation();
        createSession();
    }

    private void setupNotificationBasket(@NotNull Menu menu) {
        BasketModelView basketVM = new ViewModelProvider(this).get(BasketModelView.class); //Sepet aliniyor
        MutableLiveData<ArrayList<Product>> mutableLiveData = basketVM.getProducts();

        final MenuItem menuItem = menu.findItem(R.id.nav_basketFragment);
        View view = menuItem.getActionView();

        final TextView txt_notification = view.findViewById(R.id.menu_item_basket_txt);
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        mutableLiveData.observe(this, new Observer<ArrayList<Product>>() { //Sepeteki veri guncellenince
            int size;

            @Override
            public void onChanged(ArrayList<Product> products) { //Ui'da guncelleniyor

                size = products.size();

                if (size == 0) {
                    txt_notification.setVisibility(View.INVISIBLE);
                } else {
                    txt_notification.setVisibility(View.VISIBLE);
                    txt_notification.setText(String.valueOf(size));
                }
            }
        });


        view.setOnClickListener(new View.OnClickListener() { //Yeni layout item'i kapattigi icin onClick'i tekrar handle ediliyor
            @Override
            public void onClick(View v) {
                NavigationUI.onNavDestinationSelected(menuItem, navController);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        setupNotificationBasket(menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }
}
