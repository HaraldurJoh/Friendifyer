package com.exam22.friendifyer.Data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.exam22.friendifyer.models.Friend


@Dao
interface IFriendDao {

    @Query("SELECT * from BeFriend order by id")
    fun getAll(): LiveData<List<BeFriend>>

    @Query("SELECT * from BeFriend order by name")
    fun getByName(s:String): LiveData<List<BeFriend>>

    @Query("SELECT name from befriend order by name")
    fun getAllNames(): LiveData<List<String>>

    @Query("SELECT * from BeFriend where id = (:id)")
    fun getById(id: Int): BeFriend

    @Insert
    fun insert(f: BeFriend)

    @Update
    fun update(f: BeFriend)

    @Delete
    fun delete(f: BeFriend)

    @Query("DELETE from BeFriend")
    fun deleteAll()
}