package com.exam22.friendifyer.Data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.lang.IllegalStateException
import java.util.concurrent.Executors

class FriendRepoInDB private constructor(private val context: Context){

    private val database: FriendDatabase = Room.databaseBuilder(context.applicationContext, FriendDatabase::class.java, "friend-database").build()
    private val friendDao = database.friendDao()

    fun getAll(): LiveData<List<BeFriend>> = friendDao.getAll()

    fun getByName(name: String): LiveData<List<BeFriend>> = friendDao.getByName(name)

    fun getAllNames(): LiveData<List<String>> = friendDao.getAllNames()

    fun getById(id: Int) = friendDao.getById(id)

    private val executor = Executors.newSingleThreadExecutor()

    fun insert(f: BeFriend) {
        executor.execute { friendDao.insert(f)}
    }

    fun update(f: BeFriend) {
        executor.execute { friendDao.update(f)}
    }

    fun delete(f: BeFriend) {
        executor.execute { friendDao.delete(f)}
    }

    fun clear(){
        executor.execute { friendDao.deleteAll()}
    }

    companion object {
        private var instance: FriendRepoInDB? = null

        fun initialize(context: Context){
            if(instance == null){
                instance = FriendRepoInDB(context)
            }

            fun get(): FriendRepoInDB {
                if (instance != null){
                    return instance!!
                }
                throw IllegalStateException("Friend Repo not initialized")
            }
        }
    }
}


