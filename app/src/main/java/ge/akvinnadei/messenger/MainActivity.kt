package ge.akvinnadei.messenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
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
        bSignUp1.setOnClickListener {  view: View ->
            clLogin.visibility=GONE;
            clRegister.visibility= View.VISIBLE;
        }
        Firebase.initialize(this)

        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("aeee")

        val usersRef = database.getReference("users")

        nickname = findViewById(R.id.tvNickName)
        password = findViewById(R.id.tvPassword)
        profession = findViewById(R.id.tvProfession)
        button_signup = findViewById(R.id.bSignUp2)
        button_signup.setOnClickListener(){
            usersRef.push().key?.let {
                usersRef.child(it).setValue(arrayListOf(
                    TestUser(nickname.text.toString(), profession.text.toString())
                ))
            }
        }
        //test

    }
}