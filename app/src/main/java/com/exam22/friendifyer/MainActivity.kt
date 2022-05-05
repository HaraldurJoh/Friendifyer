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
import com.exam22.friendifyer.models.Friend
import com.exam22.friendifyer.models.FriendList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friendlist)

        val adapter = FriendAdapter(this, FriendList.getAllFriends())

    }

    internal class FriendAdapter(context: Context, private val friends: Array<Friend>
    ) : ArrayAdapter<Friend>(context, 0, friends){
        private val colours = intArrayOf(
            Color.parseColor("#AAAAAA"),
            Color.parseColor("#CCCCCC")
        )

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
            val favoriteView = resView.findViewById<ImageView>(R.id.imgFavoriteExt)
            nameView.text = f.name
            phoneView.text = f.phone

            favoriteView.setImageResource(if (f.isFavorite) R.drawable.ok else R.drawable.notok)

            return resView
        }
    }

}