package com.exam22.friendifyer.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class BeFriend(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var phone: String,
    var bestFriend: Boolean) : Serializable{

    public override fun toString(): String {
        return "$id: Name: $name, Phone: $phone, IsBestFriend: $bestFriend"
    }
}
