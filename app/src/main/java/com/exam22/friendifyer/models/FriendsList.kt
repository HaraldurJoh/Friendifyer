package com.exam22.friendifyer.models

class FriendsList {

    val mFriends = arrayOf<Friend>(
        Friend("Thyregod","63499037", true),
        Friend("Dundvig","40832243", true),
        Friend("Grasland","78914416", true),
        Friend("Peder","88467811", true),
        Friend("Aben","57382219", true),
        Friend("Mikael","20579997", true),
        Friend("Quapper","60272387", true),

    )

    fun getAll(): Array<Friend> = mFriends


    fun getAllNames(): Array<String> = mFriends.map { p -> p.name }.toTypedArray()

}