package com.exam22.friendifyer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.exam22.friendifyer.Data.BeFriend
import com.exam22.friendifyer.Data.FriendRepoInDB
import kotlinx.android.synthetic.main.activity_add_friend.*

class AddFriendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)


        btnCreateFriend.setOnClickListener{ onCreatebtnClick() }
    }

    private fun onCreatebtnClick() {
        val friend : BeFriend = BeFriend(0,tf_addName.text.toString(),tf_addPhone.text.toString(), bestFriendorNot())
        FriendRepoInDB.initialize(this)
        var fRep = FriendRepoInDB.get()
        fRep.insert(friend)
        finish()
    }

    private fun bestFriendorNot(): Boolean{
        if(tf_setBestFriend.text.toString() == "yes") return true
        else return false
    }
}