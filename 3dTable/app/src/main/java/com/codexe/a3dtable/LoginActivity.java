package com.codexe.a3dtable;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.codexe.a3dtable.TestUtils.LoginControl;

public class LoginActivity extends AppCompatActivity {


    private ProgressBar progressBar;


    private void init() {
        progressBar = findViewById(R.id.PrgbarLoading);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void BtnLogin_Click(View view) {


        view.setEnabled(false);
        LoginControl loginControl = new LoginControl(progressBar, this);
        loginControl.execute();


    }
}
