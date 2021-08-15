package ge.akvinnadei.messenger

import android.content.Context
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
        preferences = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE)
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
                }else{
                    usersRef.push().key?.let {
                        usersRef.child(it).setValue(
                            User(it, etUserName.text.toString(), etPassword.text.hashCode(), etProfession.text.toString())
                        )
                    }
                    loginSuccess(User("0", etUserName.text.toString(), 0, etProfession.text.toString()))
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, R.string.Unknown_error, Toast.LENGTH_SHORT).show()
                return
            }
        })

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
                    Toast.makeText(this@LoginActivity, getString(R.string.userName_nonExistant), Toast.LENGTH_SHORT)
                }else{
                    val entry = us.iterator().next().value
                    if(entry.passwordHash != etPassword.text.toString().hashCode()){
                        Toast.makeText(this@LoginActivity, getString(R.string.wrong_password), Toast.LENGTH_SHORT)
                        return
                    }
                    loginSuccess(entry)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, getString(R.string.Unknown_error), Toast.LENGTH_SHORT).show()
                return
            }
        })

    }

    private fun loginSuccess(entry : User) {
        preferences.edit().putString("userName", entry.userName).apply()
        preferences.edit().putString("profession", entry.profession).apply()
        startActivity(Intent(this, MainActivity::class.java))
    }

}
