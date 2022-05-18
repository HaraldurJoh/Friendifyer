package com.exam22.friendifyer.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BeFriend(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var phone: String,
    var bestFriend: Boolean){

    public override fun toString(): String {
        return "$id: Name: $name, Phone: $phone, IsBestFriend: $bestFriend"
    }
}
