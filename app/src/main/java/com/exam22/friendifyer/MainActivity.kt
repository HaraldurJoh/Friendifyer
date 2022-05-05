package com.exam22.friendifyer


import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.exam22.friendifyer.databinding.ActivityMainBinding
import com.exam22.friendifyer.databinding.CellExtendedBinding
import com.exam22.friendifyer.models.Friend
import com.exam22.friendifyer.models.FriendList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = FriendAdapter(this, FriendList().getAllFriends())
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.lvFriendList.adapter = adapter




    }


    internal class FriendAdapter(context: Context, private val friends: Array<Friend>
    ) : ArrayAdapter<Friend>(context, 0, friends){
        private val colours = intArrayOf(
            Color.parseColor("#AAAAAA"),
            Color.parseColor("#CCCCCC")
        )
        private lateinit var binding: CellExtendedBinding

        override fun getView(position: Int, v: View?, parent: ViewGroup): View {
            var v1: View? = v
            if (v1 == null) {
                val mInflater = LayoutInflater.from(context)
                v1 = mInflater.inflate(R.layout.cell_extended, null)

            }
            val resView: View = v1!!

            resView.setBackgroundColor(colours[position % colours.size])
            val f = friends[position]
            val nameView = resView.findViewById<TextView>(R.id.tvNameExt)
            val phoneView = resView.findViewById<TextView>(R.id.tvPhoneExt)
            val locationView = resView.findViewById<TextView>(R.id.tvLocationExt)
            val profileView = resView.findViewById<ImageView>(R.id.profilepicture)
            nameView.text = f.name
            phoneView.text = f.phone
            locationView.text = f.location.adress
            profileView.setImageResource(if (f.profilePicture == null) R.drawable.no_profile else R.drawable.no_profile)

            return resView
        }
    }

}