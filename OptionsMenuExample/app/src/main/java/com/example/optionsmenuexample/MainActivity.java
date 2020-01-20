package com.example.optionsmenuexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.optionsmenuexample.OptionMenu.MenuHelper;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private MenuHelper menuHelper; //menu yardımıcı sınıfı örneği


    private void init(){
        menuHelper = new MenuHelper(this);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//menu elemanı seçildiğinde çalışan metod
        super.onOptionsItemSelected(item);
        return menuHelper.ItemSelect(item.getItemId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // option menuyu oluşturan metod
        return menuHelper.Build(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}
