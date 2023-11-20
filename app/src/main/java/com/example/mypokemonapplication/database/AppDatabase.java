package com.example.mypokemonapplication.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mypokemonapplication.dao.UsersDao;
import com.example.mypokemonapplication.model.LoginUser;

@Database(entities = {LoginUser.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract UsersDao usersDao();
    
}
