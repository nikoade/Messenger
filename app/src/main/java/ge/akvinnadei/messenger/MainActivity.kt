package ge.akvinnadei.messenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import ge.akvinnadei.messenger.model.TestUser
import ge.akvinnadei.messenger.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var button_signup: Button
    lateinit var nickname: EditText
    lateinit var password: EditText
    lateinit var profession: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //test

    }
}