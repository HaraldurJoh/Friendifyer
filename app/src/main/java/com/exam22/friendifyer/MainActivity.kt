package com.exam22.friendifyer


import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.exam22.friendifyer.databinding.ActivityMainBinding
import com.exam22.friendifyer.models.Friend
import com.exam22.friendifyer.models.FriendsList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ListAdapter(this, FriendsList().getAll())

        lvFriendList.adapter = adapter
        /*
        lvFriendList.isClickable
        lvFriendList.setOnItemClickListener(AdapterView.OnItemClickListener(){
            override fun onItemClick(parent: AdapterView<?>, v: View?, Position: Int, id: Long){

            }
        }

         */
    }

    internal class ListAdapter(context: Context, private val friends : Array<Friend>) : ArrayAdapter<Friend>(context, 0, friends){

        private val colours = intArrayOf(
            Color.parseColor("#AAAAAA"),
            Color.parseColor("#CCCCCC")
        )

        override fun getView(position: Int, v: View?, parent: ViewGroup): View {
            var v1: View? = v
            if (v1 == null) {
                val mInflater = LayoutInflater.from(context)
                v1 = mInflater.inflate(R.layout.list_item, null)
            }
            val resView: View = v1!!
            resView.setBackgroundColor(colours[position % colours.size])
            val f = friends[position]
            val imageView = resView.findViewById<ImageView>(R.id.profile_image)
            val nameView = resView.findViewById<TextView>(R.id.personName)
            val phoneNumber = resView.findViewById<TextView>(R.id.phoneNumber)
            nameView.text = f.name
            phoneNumber.text = f.phone


            return resView
        }
    }
}