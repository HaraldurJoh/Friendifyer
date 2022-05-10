package com.exam22.friendifyer

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_friend.*

class FriendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        print("this is working")
        if (intent.extras != null){
            val bundle = intent.extras!!
            println("we have intent")
            name_profile.text = bundle.getString("name")
            phone_profile.text = bundle.getString("phone")
        }
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_friend)



    }
}