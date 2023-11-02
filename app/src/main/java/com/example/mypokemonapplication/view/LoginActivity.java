package com.example.mypokemonapplication.view;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mypokemonapplication.AllPokemonsActivity;
import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.Utils.Status;
import com.example.mypokemonapplication.databinding.ActivityLoginBinding;
import com.example.mypokemonapplication.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

        binding.setLifecycleOwner(this);

        binding.setLoginViewModel(loginViewModel);

        loginstatus();
    }

    private void loginstatus() {
        loginViewModel.getLoginStatus().observe(this, status -> {
            switch (status) {
                case Status.loginSuccess:
                    //xu ly dang nhap thanh cong
                    Toast.makeText(LoginActivity.this, "Login success.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, AllPokemonsActivity.class);
                    startActivity(intent);
                    break;
                case Status.emptyEmail:
                    //thong bao Toast message hoac hien thi loi Email trong
                    binding.txtEmailAddress.setError(getString(R.string.emptyemail));
                    binding.txtEmailAddress.requestFocus();
                    break;
                case Status.emptyPassWord:
                    //tuong tu cho password
                    binding.txtPassword.setError(getString(R.string.emptypassword));
                    binding.txtPassword.requestFocus();
                    break;
                case Status.isEmail:
                    binding.txtEmailAddress.setError(getString(R.string.emailformat));
                    binding.txtEmailAddress.requestFocus();
                    break;

            }

        });
    }
}
