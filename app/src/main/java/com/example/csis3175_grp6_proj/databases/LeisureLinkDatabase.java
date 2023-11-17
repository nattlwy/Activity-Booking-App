package com.example.csis3175_grp6_proj.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.csis3175_grp6_proj.interfaces.SportDao;
import com.example.csis3175_grp6_proj.interfaces.UserDao;
import com.example.csis3175_grp6_proj.models.Sport;
import com.example.csis3175_grp6_proj.models.User;

@Database(entities = {Sport.class, User.class}, version = 1, exportSchema = false)
public abstract class LeisureLinkDatabase extends RoomDatabase {
    public abstract SportDao sportDao();
    public abstract UserDao userDao();
}
