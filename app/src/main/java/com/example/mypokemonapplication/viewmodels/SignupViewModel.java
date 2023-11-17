package com.example.mypokemonapplication.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mypokemonapplication.Utils.Status;
import com.example.mypokemonapplication.Utils.ValidatorUtil;
import com.example.mypokemonapplication.model.LoginUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignupViewModel extends ViewModel {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
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
                auth.createUserWithEmailAndPassword(loginUser.getUsername(), loginUser.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            writeNewUser();
                            SignupStatus.setValue(Status.signupSuccess);
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthUserCollisionException e){
                                SignupStatus.setValue(Status.emailExisted);
                            } catch (Exception e){
                                SignupStatus.setValue(Status.signupFails);
                            }
                        }
                    }
                });
            }
        } catch (Exception ex) {
            Log.e("Signup viewmodel",ex.getMessage());
        }
    }

    public void writeNewUser(){
        String uid = auth
                .getInstance()
                .getCurrentUser()
                .getUid();
        DatabaseReference db = FirebaseDatabase
                .getInstance("https://my-pokemon-application-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference();
        LoginUser newUser = new LoginUser(loginUser.getName(), loginUser.getUsername());
        db.child("users").child(uid).setValue(newUser);
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
            } else if (ValidatorUtil.weakPassword(loginUser.getPassword())) {
                SignupStatus.setValue(Status.weakPassword);
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
