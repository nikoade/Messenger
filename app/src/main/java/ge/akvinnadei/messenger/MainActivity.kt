package ge.akvinnadei.messenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bSignUp1.setOnClickListener {  view: View ->
            clLogin.visibility=GONE;
            clRegister.visibility= View.VISIBLE;
        }
        //test
        Firebase.initialize(this)
        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("aeee")
    }
}