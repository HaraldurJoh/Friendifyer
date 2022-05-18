package com.exam22.friendifyer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import com.exam22.friendifyer.Data.BeFriend
import kotlinx.android.synthetic.main.activity_friend.*

class FriendActivity : AppCompatActivity() {
    private lateinit var selectFriend: BeFriend
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
        btnCallFriend.setOnClickListener{ onClickCall() }
        btnSMSFriend.setOnClickListener { onClickSms() }
    }

    private fun onClickCall() {
        var number = selectFriend.phone
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
    }

    private fun onClickSms() {
        val sendIntent = Intent(Intent.ACTION_VIEW)
        var number = selectFriend.phone
        sendIntent.data = Uri.parse("sms:$number")
        startActivity(sendIntent)
    }

    fun OnEditClick(view: View) {

    }


    fun onPhotoClick(view: View) {}
}

