package ge.akvinnadei.messenger

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.akvinnadei.messenger.chat.MainRecyclerAdapter
import ge.akvinnadei.messenger.model.Chat
import ge.akvinnadei.messenger.model.Message
import ge.akvinnadei.messenger.model.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.buttons.*

class MainActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE)
        recyclerView = findViewById<RecyclerView>(R.id.vRecycler)
        recyclerView.adapter = MainRecyclerAdapter(arrayListOf(), "")

        setUpView()
        bProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpView() {
        var database = Firebase.database
        var currentUser = preferences.getString("userName", "")
        val chatsRef = database.getReference("chats")
        val q = chatsRef.orderByKey()

        q.addListenerForSingleValueEvent(object: ValueEventListener{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(snapshot: DataSnapshot) {
                val mp = snapshot.getValue<Map<String, Map<String, Message>>>()
                if(mp == null || mp.count() == 0){
                    return
                }else{
                    var list = ArrayList<Chat>()
                    mp.forEach{ (key, value) -> createChat(key, value, list)}
                    recyclerView.adapter = MainRecyclerAdapter(list, currentUser.toString())

                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, getString(R.string.Unknown_error), Toast.LENGTH_SHORT).show()
                return
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChat(key: String, value: Map<String, Message>, list: ArrayList<Chat>) {
        val currentUser = preferences.getString("userName", "")
        var lastMessage = Message()
        var mxDate = 0.toLong()
        for ((key, message) in value){
            if(message.date!! > mxDate){
                mxDate = message.date
                lastMessage = message
            }
        }
        val sender = lastMessage.sender
        val receiver = lastMessage.receiver
        var friend : String
        if(sender == currentUser){
            friend = receiver.toString()
        }else{
            friend = sender.toString()
        }
        val chat = Chat(key, friend, lastMessage)
        list.add(chat)
    }
}