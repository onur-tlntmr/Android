package com.codexe.a3dtable.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.codexe.a3dtable.R;
import com.codexe.a3dtable.TestUtils.LoginControl;
import com.codexe.a3dtable.ui.LoginViewModel;
import com.codexe.a3dtable.ui.register.RegisterFragment;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;

    private EditText edt_usr_name;
    private EditText edt_password;
    private Button btn_login;
    private ProgressBar progressBar;
    private TextView txt_register;
    private FragmentTransaction transaction;

    private void init(View root) {
        edt_usr_name = root.findViewById(R.id.ac_login_mail);
        edt_password = root.findViewById(R.id.ac_login_password);
        btn_login = root.findViewById(R.id.ac_btn_login);
        txt_register = root.findViewById(R.id.fr_login_txt_register);
        progressBar = root.findViewById(R.id.ac_login_progressbar);

        transaction = getActivity().getSupportFragmentManager().beginTransaction();
    }


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        init(root);
        registerHandlers();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

    }

    private void btnLoginClick() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setEnabled(false);

                String user_name = edt_usr_name.getText().toString();
                String password = edt_password.getText().toString();

                LoginControl loginControl = new LoginControl(progressBar, requireActivity(), user_name, password);
                loginControl.execute();


            }
        });
    }

    private void txtRegisterClick() {
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.container, RegisterFragment.newInstance()).commitNow();

            }
        });
    }

    private void registerHandlers() {
        btnLoginClick();
        txtRegisterClick();
    }


}
