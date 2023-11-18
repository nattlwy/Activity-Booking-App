package com.example.csis3175_grp6_proj.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.csis3175_grp6_proj.models.User;

import java.util.List;
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUsers(User... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertUsersFromList(List<User> Users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOneUser(User user);

    @Query("SELECT * FROM users")
    List<User> GetAllUsers();

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    User getUserByEmailAndPassword(String email, String password);

    @Query("SELECT * FROM users ORDER BY UserId DESC LIMIT 1")
    User getLastInsertedUser();

}
