package com.codexe.a3dtable.TestUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.codexe.a3dtable.MainActivity;


public class LoginControl extends AsyncTask<Void, Void, Boolean> {

    private Context context;

    private ProgressBar progressBar;


    public LoginControl(ProgressBar progressBar, Context context) {
        this.progressBar = progressBar;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    protected Boolean doInBackground(Void... voids) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Log.e("csd", e.getMessage());
        }


        return new Boolean(true);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            ((Activity) context).finish();

            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            context.startActivity(intent);

        }
    }
}