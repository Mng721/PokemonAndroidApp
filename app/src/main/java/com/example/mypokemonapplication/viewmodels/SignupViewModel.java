package com.example.mypokemonapplication.viewmodels;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mypokemonapplication.Utils.Status;
import com.example.mypokemonapplication.Utils.ValidatorUtil;
import com.example.mypokemonapplication.model.LoginUser;
import com.example.mypokemonapplication.repository.AuthenticationRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupViewModel extends AndroidViewModel {

    private AuthenticationRepository repository;
    private MutableLiveData<FirebaseUser> userData;

    public SignupViewModel(@NonNull Application application) {
        super(application);

        repository = new AuthenticationRepository(application);
    }

    public void register(){
        try {
            if (validateData()) {
//               //call api login here
                SignupStatus.setValue(Status.signupSuccess);
                repository.register(loginUser.getUsername(), loginUser.getPassword());
            }
        } catch (Exception ex) {
            Log.e("Signup viewmodel",ex.getMessage());
        }
    }
    public MutableLiveData<Integer> getSignupStatus(){return SignupStatus;}
    private final MutableLiveData<Integer> SignupStatus = new MutableLiveData<>();

    private final MutableLiveData<Boolean> onEndLive = new MutableLiveData<>();

    public MutableLiveData<Boolean> getOnEndLive(){
        return onEndLive;
    }

    private final LoginUser loginUser = new LoginUser();

    public LoginUser getLoginUser(){return loginUser;}

    public void onClickSignUp(){
        try {
            if (validateData()) {
//               //call api login here
                SignupStatus.setValue(Status.signupSuccess);
            }
        } catch (Exception ex) {
            Log.e("Signup viewmodel",ex.getMessage());
        }
    }

    public void onClickFinish(){
        onEndLive.setValue(true);
    }

    private boolean validateData() {
        try {
            if (ValidatorUtil.emptyValue(loginUser.getName())) {
                SignupStatus.setValue(Status.emptyName);
                return false;
            } else if (ValidatorUtil.emptyValue(loginUser.getUsername())) {
                SignupStatus.setValue(Status.emptyEmail);
                return false;
            } else if (ValidatorUtil.emptyValue(loginUser.getPassword())) {
                SignupStatus.setValue(Status.emptyPassWord);
                return false;
            } else if (!ValidatorUtil.isEmail(loginUser.getUsername())) {
                SignupStatus.setValue(Status.isEmail);
                return false;
            }
        } catch (Exception ex) {
            Log.e("Validate viewmodel",ex.getMessage());
            SignupStatus.setValue(Status.signupFails);
        }
        return true;
    }
}
