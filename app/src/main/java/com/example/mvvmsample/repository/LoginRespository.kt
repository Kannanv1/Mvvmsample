package com.example.mvvmsample.repository

import com.example.mvvmsample.data.UserDao
import com.example.mvvmsample.models.User

class LoginRespository {
    class LoginRepository(private val userDao: UserDao) {
        suspend fun login(username: String, password: String): Boolean {
            val user = userDao.getUser(username, password)
            return user != null
        }

        suspend fun register(username: String, password: String): Boolean {
            return try {
                userDao.insertUser(User(username, password))
                true
            } catch (e: Exception) {
                false
            }
        }
        suspend fun getAllUsers(): List<User> {
            return userDao.getAllUsers()
        }
    }


}