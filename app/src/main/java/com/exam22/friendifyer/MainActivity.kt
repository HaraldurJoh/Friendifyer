package com.exam22.friendifyer


import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.exam22.friendifyer.Data.BeFriend
import com.exam22.friendifyer.Data.FriendRepoInDB
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FriendRepoInDB.initialize(this)
        insertTestData()
        val fRep = FriendRepoInDB.get()
        val listOfFriend = fRep.getAll().value
        val adapterTwo = CustomListAdapter(this, listOfFriend)

        lvFriendList.adapter = adapterTwo
        lvFriendList.isClickable
        lvFriendList.setOnItemClickListener{_,_,pos, _ -> onFriendClick(pos)}

        // setupDataObserver()

    }

    private fun insertTestData(){
        var hasRun = false
        if(hasRun == false) {
            val fRep = FriendRepoInDB.get()
            fRep.clear()
            fRep.insert(BeFriend(0,"Thyregod","63499037", false))
            fRep.insert(BeFriend(0,"Dundvig","40832243", true))
            fRep.insert(BeFriend(0,"Grasland","78914416", true))
            fRep.insert(BeFriend(0,"Peder","88467811", false))
            fRep.insert(BeFriend(0,"Aben","57382219", false))
            fRep.insert(BeFriend(0,"Mikael","20579997", false))
            fRep.insert(BeFriend(0,"Quapper","60272387", true))
            hasRun = true
        }

        /*
        Friend(1,"Thyregod","63499037", false),
        Friend(2,"Dundvig","40832243", true),
        Friend(3, "Grasland","78914416", true),
        Friend(4,"Peder","88467811", false),
        Friend(5,"Aben","57382219", false),
        Friend(6,"Mikael","20579997", false),
        Friend(7,"Quapper","60272387", true)
         */
    }

    /*
    private fun setupDataObserver() {

        val mRep = FriendRepoInDB.get()
        val getAllObserver = Observer<List<BeFriend>>{ f ->
            val adapter: ListAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                f)
            lvFriendList.adapter = adapter
            Log.d("xyz", "getAll observer notified")

        }
        mRep.getAll().observe(this, getAllObserver)

        lvFriendList.onItemClickListener = AdapterView.OnItemClickListener { _, _, pos, _ -> onFriendClick(pos)}


    }
    */

    private fun onFriendClick(pos: Int) {
        val clickedFriend = lvFriendList.getItemAtPosition(pos) as BeFriend
        println(clickedFriend.name)
        val b = Bundle()
        b.putString("name",clickedFriend.name)
        b.putString("phone",clickedFriend.phone)
        b.putBoolean("bestFriend",clickedFriend.bestFriend)
        b.putInt("id",clickedFriend.id)
        startFriendActivity(b)
    }

    private fun onClickPerson(listView: AdapterView<*>, pos: Int) {
        val f = listView.getItemAtPosition(pos) as BeFriend
        Toast.makeText(this, "You have clicked ${f.name} at position $pos", Toast.LENGTH_LONG).show()
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

    internal class CustomListAdapter(context: Context, private val friends: List<BeFriend>) : ArrayAdapter<BeFriend>(context, 0, friends){

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