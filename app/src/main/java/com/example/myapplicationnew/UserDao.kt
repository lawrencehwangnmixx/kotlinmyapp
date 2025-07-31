package com.example.myapplicationnew


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplicationnew.data.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun InsertUser (user: User)
    @Query("SELECT username FROM User WHERE account = :account AND password = :password LIMIT 1")
    suspend fun CheckUser (account : String , password : String):String?
    @Query("SELECT account FROM User WHERE account = :account LIMIT 1")
    suspend fun checkaccountexist (account : String):String?
    @Query("SELECT username FROM User WHERE username = :username LIMIT 1")
    suspend fun checkusernameexiset (username : String):String?
}