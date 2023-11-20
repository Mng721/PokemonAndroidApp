package com.example.mypokemonapplication.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mypokemonapplication.model.LoginUser;

import java.util.List;

@Dao
public interface UsersDao {
//    single user insert
    @Insert
    public Long insertUser(LoginUser user);
//    insert list of user

    @Query("SELECT * FROM Users WHERE username LIKE :username AND password LIKE :password")
    public LoginUser readLoginData(String username, String password);

    @Query("select * from users where uid Like :uid")
    public LoginUser getUserDataDetail(Long uid);


    //deleting all user from db
    @Query("DELETE FROM Users")
    public void deleteAll();
}
