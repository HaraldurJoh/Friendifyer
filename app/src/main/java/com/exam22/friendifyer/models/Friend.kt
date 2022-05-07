package com.exam22.friendifyer.models

import android.media.Image
import java.io.Serializable

data class Friend(var name: String, var phone: String, var bestFriend: Boolean): Serializable{

}