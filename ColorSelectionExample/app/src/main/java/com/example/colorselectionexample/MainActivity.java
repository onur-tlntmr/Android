package com.example.colorselectionexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.colorselectionexample.MainMenu.MenuHelper;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor edt;
    private LinearLayout pnlTest;
    private SeekBar sbRed,sbGreen,sbBlue;
    private TextView txtRGB ,txtHEX;
    private MenuHelper menuHelper;
    private int r,g,b;



    private void init(){
        sp = this.getSharedPreferences("ColorSettings", Context.MODE_PRIVATE);

        pnlTest = (LinearLayout)findViewById(R.id.pnlTest);

        sbRed = (SeekBar)findViewById(R.id.sbRed);
        sbGreen = (SeekBar)findViewById(R.id.sbGreen);
        sbBlue = (SeekBar)findViewById(R.id.sbBlue);

        txtRGB = (TextView)findViewById(R.id.txtRGB);
        txtHEX = (TextView)findViewById(R.id.txtHEX);

        menuHelper = new MenuHelper(this,sp);
    }

    private void sbRedChanged(){
        sbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r = seekBar.getProgress();
                Paint();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void sbGreenChanged(){
        sbGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                g = seekBar.getProgress();
                Paint();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void sbBlueChanged(){
        sbBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                b = seekBar.getProgress();
                Paint();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void registerHandlers(){
        sbRedChanged();
        sbGreenChanged();
        sbBlueChanged();
    }



    private void Paint() {
        pnlTest.setBackgroundColor(Color.rgb(r,g,b));

        txtRGB.setText(String.format("R: %d G: %d B: %d",r,g,b));
        txtHEX.setText(String.format("#%X%X%X",r,g,b));

    }

    public void Paint(int r, int g, int b) {
        pnlTest.setBackgroundColor(Color.rgb(r,g,b));

        txtRGB.setText(String.format("R: %d G: %d B: %d",r,g,b));
        txtHEX.setText(String.format("#%X%X%X",r,g,b));

        sbRed.setProgress(r);
        sbGreen.setProgress(g);
        sbBlue.setProgress(b);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return menuHelper.Build(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return menuHelper.ItemSelect(item.getItemId(),new Object[]{r,g,b});
    }


    @Override
    public void onStop() {
        super.onStop();

        AppSettings appSettings = new AppSettings(this,sp);
        appSettings.Seriliaze(r,g,b);
    }

    private void Reload(){
        AppSettings as = new AppSettings(this,sp);
        as.Deseriliaze();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        registerHandlers();
        Reload();
    }


}
