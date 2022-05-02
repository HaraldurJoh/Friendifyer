package com.exam22.friendifyer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friendlist)
    }

    fun onClickFriends(view: View) {
        val intent = Intent(this, ActivityFriendslist::class.java)
            startActivity(intent)
    }
}