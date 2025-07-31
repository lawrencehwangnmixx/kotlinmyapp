package com.example.myapplicationnew.data.repositor

import com.example.myapplicationnew.UserDao
import com.example.myapplicationnew.data.User

class UserRepository(private val userDao: UserDao) {

    suspend fun CheckUser(account: String, password: String): String? {
        return userDao.CheckUser(account, password)
    }

    suspend fun insertUser(user: User) {
        userDao.InsertUser(user)
    }

    suspend fun checkaccountexist(account: String): String? {
        return userDao.checkaccountexist(account)
    }

    suspend fun checkusernameexiset(username: String): String? {
        return userDao.checkusernameexiset(username)
    }
}