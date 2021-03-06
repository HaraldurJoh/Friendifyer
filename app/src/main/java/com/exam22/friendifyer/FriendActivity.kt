package com.exam22.friendifyer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import com.exam22.friendifyer.Data.BeFriend
import com.exam22.friendifyer.Data.FriendRepoInDB
import kotlinx.android.synthetic.main.activity_friend.*
import java.io.Serializable

class FriendActivity : AppCompatActivity(), Serializable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)
        var selectFriend: BeFriend =  intent.getSerializableExtra("clicked") as BeFriend

        if (intent.extras != null){
            val b = intent.extras!!
            println("we have intent")
            tf_name.setText(selectFriend.name)
            tf_phone.setText(selectFriend.phone)
            val bf = b.getBoolean("bestFriend")
            if (bf == true){
                tf_bestFriend.setText("yes")
            } else {
                tf_bestFriend.setText("no")
            }
        }

        btnCallFriend.setOnClickListener{ onClickCall(selectFriend) }
        btnSMSFriend.setOnClickListener { onClickSms(selectFriend) }
        btnEditFriend.setOnClickListener { onEditClick(selectFriend)}

    }

    private fun onClickCall(selectFriend: BeFriend) {
        var number = selectFriend.phone
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
    }

    private fun onClickSms(selectFriend: BeFriend) {
        val sendIntent = Intent(Intent.ACTION_VIEW)
        var number = selectFriend.phone
        sendIntent.data = Uri.parse("sms:$number")
        startActivity(sendIntent)
    }
    fun onEditClick(selectFriend: BeFriend) {
        var newFriendInfo = selectFriend
        newFriendInfo.name = tf_name.text.toString()
        newFriendInfo.phone = tf_phone.text.toString()
        newFriendInfo.bestFriend = tf_bestFriend.text.toString() == "yes"
        editFriend(newFriendInfo)
    }


    fun editFriend(friend: BeFriend){
        FriendRepoInDB.initialize(this)
        var fRep = FriendRepoInDB.get()
        fRep.update(friend)

    }
    fun onClickPhoto(view: View){
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }
}

