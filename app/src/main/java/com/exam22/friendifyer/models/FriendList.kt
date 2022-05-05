package com.exam22.friendifyer.models

import android.media.Image

class FriendList {
    val friendList = arrayOf<Friend>(
        Friend("Thyregod","12345678",true,Location("Negervej 81","NegerBy","42069"), null),
        Friend("Thyregod1","12345678",true,Location("Negervej 81","NegerBy","42069"), null),
        Friend("Thyregod2","12345678",true,Location("Negervej 81","NegerBy","42069"), null),
        Friend("Thyregod3","12345678",true,Location("Negervej 81","NegerBy","42069"), null),
        Friend("Thyregod4","12345678",true,Location("Negervej 81","NegerBy","42069"), null),
    )

    fun getAllFriends(): Array<Friend> = friendList

    fun getAllNames(): Array<String> = friendList.map { p -> p.name }.toTypedArray()
}