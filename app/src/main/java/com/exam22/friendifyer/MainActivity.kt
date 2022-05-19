package com.exam22.friendifyer


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.ContextMenu.ContextMenuInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.exam22.friendifyer.Data.BeFriend
import com.exam22.friendifyer.Data.FriendRepoInDB
import kotlinx.android.synthetic.main.activity_camera.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable
import java.util.*


class MainActivity : AppCompatActivity(), Serializable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FriendRepoInDB.initialize(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_delete->Toast.makeText(this, "People", Toast.LENGTH_SHORT).show()
            R.id.nav_favorite->Toast.makeText(this, "favorite", Toast.LENGTH_SHORT).show()
            R.id.nav_edit->Toast.makeText(this, "edit", Toast.LENGTH_SHORT).show()
            R.id.nav_create->Toast.makeText(this, "create", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        setupDataObserver()
    }

    private fun insertTestData() {
        var hasRun = false
        if (hasRun == false) {
            val fRep = FriendRepoInDB.get()
            fRep.clear()
            fRep.insert(BeFriend(0, "Thyregod", "63499037", false))
            fRep.insert(BeFriend(0, "Dundvig", "40832243", true))
            fRep.insert(BeFriend(0, "Grasland", "78914416", true))
            fRep.insert(BeFriend(0, "Peder", "88467811", false))
            fRep.insert(BeFriend(0, "Aben", "57382219", false))
            fRep.insert(BeFriend(0, "Mikael", "20579997", false))
            fRep.insert(BeFriend(0, "Quapper", "60272387", true))
            hasRun = true
        }
    }


    private fun setupDataObserver() {

        val fRep = FriendRepoInDB.get()
        val getAllObserver = Observer<List<BeFriend>> { f ->
            val adapter: ListAdapter = CustomListAdapter(
                this,
                f
            )
            lvFriendList.adapter = adapter
            Log.d("xyz", "getAll observer notified")

        }
        fRep.getAll().observe(this, getAllObserver)

        lvFriendList.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, pos, _ -> onFriendClick(pos) }


    }

    private fun test(s: Serializable) {

    }

    private fun onFriendClick(pos: Int) {
        val clickedFriend = lvFriendList.getItemAtPosition(pos) as BeFriend
        println(clickedFriend.name)
        val intent = Intent(this, FriendActivity::class.java).apply {
            putExtra("clicked", clickedFriend)
        }
        val b = Bundle()
        b.putString("name", clickedFriend.name)
        b.putString("phone", clickedFriend.phone)
        b.putBoolean("bestFriend", clickedFriend.bestFriend)
        b.putInt("id", clickedFriend.id)
        startActivity(intent)
    }

    private fun onClickPerson(listView: AdapterView<*>, pos: Int) {
        val f = listView.getItemAtPosition(pos) as BeFriend
        Toast.makeText(this, "You have clicked ${f.name} at position $pos", Toast.LENGTH_LONG)
            .show()
    }

    fun onClickClear(view: View) {
        val mRep = FriendRepoInDB.get()
        mRep.clear()
    }

    private fun startFriendActivity(b: Bundle) {
        val newIntent = Intent(this, FriendActivity::class.java)
        newIntent.putExtras(b)
        startActivity(newIntent)
    }

    internal class CustomListAdapter(context: Context, private val friends: List<BeFriend>) :
        ArrayAdapter<BeFriend>(context, 0, friends) {

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
            val bestFriend = resView.findViewById<TextView>(R.id.isBestFriend)
            nameView.text = f.name
            phoneNumber.text = f.phone
            if (f.bestFriend) {
                bestFriend.text = "Best Friend"
            } else {
                bestFriend.text = "Friend"
            }

            return resView
        }
    }
}


