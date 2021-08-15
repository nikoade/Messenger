package ge.akvinnadei.messenger.chat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import ge.akvinnadei.messenger.R
import androidx.recyclerview.widget.RecyclerView
import ge.akvinnadei.messenger.ChatActivity
import ge.akvinnadei.messenger.ProfileActivity
import ge.akvinnadei.messenger.model.Chat
import java.time.*
import java.util.*


class MainRecyclerAdapter(var chats: List<Chat>, val sender: String): RecyclerView.Adapter<ChatsViewHolder>() {
    override fun getItemCount(): Int {
        return chats.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.short_chat, parent, false)
        return ChatsViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        val chat = chats[position]
        val imgView = holder.itemView.findViewById<ImageView>(R.id.imgPhoto)
        val tvUserName = holder.itemView.findViewById<TextView>(R.id.tvUserName)
        val tvLastMessage = holder.itemView.findViewById<TextView>(R.id.tvLastMessage)
        val tvLastMessageTime = holder.itemView.findViewById<TextView>(R.id.tvLastMessageTime)
        imgView.setImageResource(R.drawable.bat);
        tvUserName.text = chat.friend
        tvLastMessage.text = chat.lastMessage!!.value
        tvLastMessageTime.text = dateDisplayFormat(chat.lastMessage.date!!)
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, ChatActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun dateDisplayFormat(milliseconds: Long): String{
//        var currentDate = LocalDate.now()
//        var format : String
//        var date = LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault())
//        val dur: Duration = Duration.between(date, currentDate)
//        val hours = dur.toHours()
//        val minutes = dur.toMinutes()
//        if(minutes < 60){
//            format = "" + minutes + " min"
//        }else if(hours < 24){
//            format = "" + hours + " hour"
//        }else{
//            format = "" + date.dayOfMonth + " " + date.month.name.substring(0,3)
//        }
//        return format
        return "15min"
    }
}


class ChatsViewHolder(view: View): RecyclerView.ViewHolder(view)