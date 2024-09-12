package com.example.mvvmsample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmsample.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    suspend fun getUser(username: String, password: String): User?

    @Insert
    suspend fun insertUser(user: User)
    // Add this method to the existing UserDao interface
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

}
