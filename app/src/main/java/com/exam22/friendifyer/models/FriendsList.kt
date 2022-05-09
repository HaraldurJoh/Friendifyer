package com.exam22.friendifyer.models

class FriendsList {

    val mFriends = arrayOf<Friend>(
        Friend(1,"Thyregod","63499037", false),
        Friend(2,"Dundvig","40832243", true),
        Friend(3, "Grasland","78914416", true),
        Friend(4,"Peder","88467811", false),
        Friend(5,"Aben","57382219", false),
        Friend(6,"Mikael","20579997", false),
        Friend(7,"Quapper","60272387", true),

    )

    fun getAll(): Array<Friend> = mFriends


    fun getAllNames(): Array<String> = mFriends.map { p -> p.name }.toTypedArray()

}