package com.exam22.friendifyer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_friend.*

class FriendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        print("this is working")
        if (intent.extras != null){
            val bundle = intent.extras!!
            println("we have intent")
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)
    }
}