package com.exam22.friendifyer.models

class FriendList {
    val friendList = arrayOf<Friend>(
        Friend("Thyregod","12345678",true,Location("Negervej 81","NegerBy","42069"))
    )

    fun getAllFriends(): Array<Friend> = friendList

    fun getAllNames(): Array<String> = friendList.map { p -> p.name }.toTypedArray()
}