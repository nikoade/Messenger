package ge.akvinnadei.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import ge.akvinnadei.messenger.model.TestUser
import kotlinx.android.synthetic.main.activity_login.*
import java.sql.DatabaseMetaData

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Firebase.initialize(this)
        var database = Firebase.database
        val myRef = database.getReference("message")
        myRef.setValue("test")
        setupView(database)
    }

    private fun setupView(database : FirebaseDatabase) {
        bLogin.setOnClickListener {  view: View ->
            login()
        }

        bSignUp2.setOnClickListener {  view: View ->
            register(database)
        }

        bSignUp1.setOnClickListener {  view: View ->
            clLogin.visibility= View.GONE;
            clRegister.visibility= View.VISIBLE;
        }
    }

    private fun register(database : FirebaseDatabase){
        // validate()
        if (!Helper.validateEmptyField(etNickName)) {
            Toast.makeText(this, getString(R.string.enter_your_nickname), Toast.LENGTH_SHORT).show()
            return
        }
        if(!Helper.validateEmptyField(etPassword)){
            Toast.makeText(this, getString(R.string.enter_your_password), Toast.LENGTH_SHORT).show()
            return
        }
        if(!Helper.validateEmptyField(etProfession)){
            Toast.makeText(this, getString(R.string.enter_your_profession), Toast.LENGTH_SHORT).show()
        }

        val usersRef = database.getReference("users")
        usersRef.push().key?.let {
            usersRef.child(it).setValue(arrayListOf(
                TestUser(etNickName.text.toString(), etPassword.text.hashCode(), etProfession.text.toString())
            ))
        }

        // TODO:: add user etNickname.text     etPassword.text
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun login(){
        // validate()
        if (!Helper.validateEmptyField(etNickName)) {
            Toast.makeText(this, getString(R.string.enter_your_nickname), Toast.LENGTH_SHORT).show()
            return
        }
        if(!Helper.validateEmptyField(etPassword)){
            Toast.makeText(this, getString(R.string.enter_your_password), Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: check login etNickname.text  etPassword.text
        startActivity(Intent(this, MainActivity::class.java))
    }

}