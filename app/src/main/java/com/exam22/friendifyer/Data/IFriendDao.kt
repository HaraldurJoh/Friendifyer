package com.exam22.friendifyer.Data

import com.exam22.friendifyer.models.Friend

interface IFriendDao {
    fun getAll(): Array<BeFriend>

    fun getByName(s:String): Array<BeFriend>

    fun getAllNames(): Array<String>

    fun getById(id: Int): BeFriend

    fun insert(f: BeFriend)

    fun update(f: BeFriend)

    fun delete(f: BeFriend)

    fun deleteAll()
}