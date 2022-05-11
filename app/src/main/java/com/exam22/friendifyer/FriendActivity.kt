package com.exam22.friendifyer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import kotlinx.android.synthetic.main.activity_friend.*

class FriendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        print("this is working")
        setContentView(R.layout.activity_friend)
        if (intent.extras != null){
            val b = intent.extras!!
            println("we have intent")
            tf_name.setText(b.getString("name"))
            tf_phone.setText(b.getString("phone"))
            val bf = b.getBoolean("bestFriend")
            if (bf == true){
                tf_bestFriend.setText("yes")
            } else {
                tf_bestFriend.setText("no")
            }
        }

    }

    fun OnEditClick(view: View) {



    }
}