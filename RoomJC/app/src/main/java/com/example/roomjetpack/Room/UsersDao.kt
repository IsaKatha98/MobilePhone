package com.example.roomjetpack.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomjetpack.Entities.Users
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("Select * from users")
    fun getUsers(): Flow<List<Users>>

    @Query("Select * from users where id=:id")
    fun getUserById(id:Int):Flow <Users>

    @Insert
    suspend fun insertUer (user:Users)

    @Update
    suspend fun updateUser(user:Users)

    @Delete
    suspend fun deleteUser(user:Users)

}