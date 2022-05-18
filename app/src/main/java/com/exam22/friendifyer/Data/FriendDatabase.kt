package com.exam22.friendifyer.Data

import androidx.room.*

@Database(entities = [BeFriend::class], version = 1)
abstract class FriendDatabase : RoomDatabase() {

    abstract fun friendDao(): IFriendDao
}