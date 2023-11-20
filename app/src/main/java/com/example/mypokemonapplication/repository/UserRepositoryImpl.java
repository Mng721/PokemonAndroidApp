package com.example.mypokemonapplication.repository;

import com.example.mypokemonapplication.dao.UsersDao;
import com.example.mypokemonapplication.model.LoginUser;

public class UserRepositoryImpl implements UserRepository{

    private UsersDao usersDao;

    public UserRepositoryImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public Long addUser(LoginUser user) {
        return usersDao.insertUser(user);
    }

    @Override
    public void deleteUser(LoginUser user) {
        usersDao.deleteAll();
    }

    @Override
    public LoginUser verifyLoginUser(String username, String password) {
        return usersDao.readLoginData(username,password);
    }

    @Override
    public LoginUser getUserDataDetails(Long uid) {
        return usersDao.getUserDataDetail(uid);
    }

}
