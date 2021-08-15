package ge.akvinnadei.messenger

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ChatAdapter(

) :
    RecyclerView.Adapter<MessagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {

        val view: View = if (viewType == SENT) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_sent, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_recieved, parent, false)
        }

        return MessagesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 30
    }

    override fun getItemViewType(position: Int): Int {
        return if (position%2 == 0) {
            SENT
        } else {
            RECEIVED
        }
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {

    }

    private fun Date.toHourAndMinuteFormat(): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(this)
    }


    companion object {
        private const val SENT = 0
        private const val RECEIVED = 1
    }
}


class MessagesViewHolder(view: View) : RecyclerView.ViewHolder(view)