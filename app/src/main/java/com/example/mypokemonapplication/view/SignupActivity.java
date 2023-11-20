package com.example.mypokemonapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mypokemonapplication.AllPokemonsActivity;
import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.Utils.Status;
import com.example.mypokemonapplication.databinding.ActivitySignupBinding;
import com.example.mypokemonapplication.viewmodels.SignupViewModel;
import com.google.firebase.FirebaseApp;

public class SignupActivity extends AppCompatActivity {

    private SignupViewModel signupViewModel;
    private ActivitySignupBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        signupViewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        binding.setLifecycleOwner(this);
        binding.setSignupViewModel(signupViewModel);
        signupViewModel.getOnEndLive().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean onEnd) {
                if (onEnd != null && onEnd) {
                    finish();
                }
            }
        });

        signupStatus();
    }

    private void signupStatus() {
        signupViewModel.getSignupStatus().observe(this, status -> {
            switch (status) {
                case Status.signupSuccess:
                    //xu ly dang ky thanh cong
                    Toast.makeText(SignupActivity.this, "Sign up successfully.", Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case Status.emptyName:
                    //thong bao Toast message hoac hien thi loi Name
                    binding.txtName.setError(getString(R.string.emptyname));
                    binding.txtName.requestFocus();
                    break;
                case Status.emptyEmail:
                    //thong bao Toast message hoac hien thi loi Email
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
