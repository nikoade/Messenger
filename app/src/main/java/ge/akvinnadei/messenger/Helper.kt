package ge.akvinnadei.messenger

import android.widget.EditText
import android.widget.TextView

class Helper {

    companion object {
        fun validateEmptyField(v : TextView) : Boolean{
            return !(v.text.isEmpty())
        }
    }
}