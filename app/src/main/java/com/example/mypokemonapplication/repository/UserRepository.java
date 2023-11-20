package com.example.mypokemonapplication.repository;

import com.example.mypokemonapplication.model.LoginUser;

public interface UserRepository {
    public Long addUser(LoginUser user);

    public void deleteUser(LoginUser user);

    public LoginUser verifyLoginUser(String username, String password);

    public LoginUser getUserDataDetails(Long uid);
}
