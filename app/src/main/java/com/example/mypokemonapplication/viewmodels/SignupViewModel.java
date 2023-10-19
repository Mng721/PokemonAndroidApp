package com.example.mypokemonapplication.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mypokemonapplication.model.LoginUser;

public class SignupViewModel extends ViewModel {

    public   MutableLiveData<Integer> getSignupStatus(){return SignupStatus;}
    private MutableLiveData<Integer> SignupStatus = new MutableLiveData<>();

    private LoginUser loginUser = new LoginUser();

    public LoginUser getLoginUser(){return loginUser;}

    public void onClickSignUp(){}
}
