package com.example.mypokemonapplication.viewmodels;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mypokemonapplication.Utils.Status;
import com.example.mypokemonapplication.Utils.ValidatorUtil;
import com.example.mypokemonapplication.model.LoginUser;
import com.example.mypokemonapplication.view.SignupActivity;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<Integer> getLoginStatus() {
        return LoginStatus;
    }

    private final MutableLiveData<Integer> LoginStatus = new MutableLiveData<>();
    private final LoginUser loginUser = new LoginUser();

    private Boolean isRemember;

    public Boolean getRemember() {
        return isRemember;
    }

    public void setRemember(Boolean remember) {
        isRemember = remember;
    }

    public void onclickLogin() {
        try {
            if (validateData()) {
//               //call api login here
                LoginStatus.setValue(Status.loginSuccess);
            }
        } catch (Exception ex) {
            Log.e("Login viewmodel",ex.getMessage());
        }
    }

    public void onClickGoToSignUp(View view){
        Context context = view.getContext();
        Intent intent = new Intent(context, SignupActivity.class);
        context.startActivity(intent);
    }

    private boolean validateData() {
        try {
            if (ValidatorUtil.emptyValue(loginUser.getUsername())) {
                LoginStatus.setValue(Status.emptyEmail);
                return false;
            } else if (ValidatorUtil.emptyValue(loginUser.getPassword())) {
                LoginStatus.setValue(Status.emptyPassWord);
                return false;
            } else if (!ValidatorUtil.isEmail(loginUser.getUsername())) {
                LoginStatus.setValue(Status.isEmail);
                return false;
            }
        } catch (Exception ex) {
            Log.e("Validate viewmodel",ex.getMessage());
            LoginStatus.setValue(Status.loginFails);
        }
        return true;
    }


    public LoginUser getLoginUser() {
        return loginUser;
    }

}
