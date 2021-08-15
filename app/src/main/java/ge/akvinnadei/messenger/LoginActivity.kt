package ge.akvinnadei.messenger

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import ge.akvinnadei.messenger.model.User
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var preferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Firebase.initialize(this)
        var database = Firebase.database
        val myRef = database.getReference("message")
        preferences = getPreferences(MODE_PRIVATE)
        setupView(database)
    }

    private fun setupView(database : FirebaseDatabase) {
        bLogin.setOnClickListener {  view: View ->
            login(database)
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
        if (!Helper.validateEmptyField(etUserName)) {
            Toast.makeText(this, getString(R.string.enter_your_userName), Toast.LENGTH_SHORT).show()
            return
        }
        if(!Helper.validateEmptyField(etPassword)){
            Toast.makeText(this, getString(R.string.enter_your_password), Toast.LENGTH_SHORT).show()
            return
        }
        if(!Helper.validateEmptyField(etProfession)){
            Toast.makeText(this, getString(R.string.enter_your_profession), Toast.LENGTH_SHORT).show()
            return
        }

        val usersRef = database.getReference("users")
        val q = usersRef.orderByChild("userName").equalTo(etUserName.text.toString())
        q.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val us = snapshot.getValue<Map<String, User>>()
                if(us != null && us.count() != 0){
                    Toast.makeText(this@LoginActivity, getString(R.string.userName_exists), Toast.LENGTH_SHORT).show()
                    return
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, R.string.Unknown_error, Toast.LENGTH_SHORT).show()
                return
            }

        })
        usersRef.push().key?.let {
            usersRef.child(it).setValue(
                User(it, etUserName.text.toString(), etPassword.text.hashCode(), etProfession.text.toString())
            )
        }

        preferences.edit().putString("userName", etUserName.text.toString())
        preferences.edit().putString("profession", etProfession.text.toString())
        startActivity(Intent(this, MainActivity::class.java))
    }


    private fun login(database : FirebaseDatabase){
        // validate()
        if (!Helper.validateEmptyField(etUserName)) {
            Toast.makeText(this, getString(R.string.enter_your_userName), Toast.LENGTH_SHORT).show()
            return
        }
        if(!Helper.validateEmptyField(etPassword)){
            Toast.makeText(this, getString(R.string.enter_your_password), Toast.LENGTH_SHORT).show()
            return
        }

        val usersRef = database.getReference("users")
        val q = usersRef.orderByChild("userName").equalTo(etUserName.text.toString())
        q.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val us = snapshot.getValue<Map<String, User>>()
                if(us == null || us.count() == 0){
                    Toast.makeText(this@LoginActivity, getString(R.string.wrong_userName_password), Toast.LENGTH_SHORT)
                    return
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, getString(R.string.Unknown_error), Toast.LENGTH_SHORT).show()
                return
            }
        })

        preferences.edit().putString("userName", etUserName.text.toString())
        preferences.edit().putString("profession", etProfession.text.toString())

        startActivity(Intent(this, MainActivity::class.java))
    }

}
