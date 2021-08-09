package ge.akvinnadei.messenger.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.Month
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class Message(_value : String, _sender : User, _receiver : User) {
    private var value : String
    private var sender : User
    private var receiver : User
    private var date : LocalDateTime

    init {
        this.value = _value
        this.sender = _sender
        this.receiver = _receiver
        this.date = LocalDateTime.now()
    }

    fun getValue(): String{
        return value;
    }

    fun setValue(_value : String){
        value = _value
    }

    fun getSender(): User{
        return sender
    }

    fun setSender(_sender: User){
        sender = _sender
    }

    fun getReceiver(): User{
        return receiver
    }

    fun setReceiver(_receiver: User){
        receiver = _receiver
    }

    fun getYear(): Int{
        return date.getYear()
    }

    fun getMonth(): Month {
        return date.getMonth()
    }

    fun getDay(): Int{
        return date.getDayOfMonth()
    }

    fun getHour(): Int{
        return date.getHour()
    }

    fun getMinute(): Int{
        return date.getMinute()
    }

    fun displayFormat(_date : LocalDateTime): String{
        var format = ""
        return format
    }
}