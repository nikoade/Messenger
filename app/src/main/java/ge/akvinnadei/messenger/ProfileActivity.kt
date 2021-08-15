package ge.akvinnadei.messenger

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.FirebaseDatabaseKtxRegistrar
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.akvinnadei.messenger.model.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.buttons.*

class ProfileActivity  : AppCompatActivity() {

    private lateinit var preferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        preferences = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE)
        var database = Firebase.database
        setupView(database)
    }

    private fun setupView(database: FirebaseDatabase) {
        var userName = preferences.getString("userName", "")
        var profession = preferences.getString("profession", "")

        etProfileUserName.setText(userName)
        etProfileProfession.setText(profession)

        bHome.setOnClickListener { view: View ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        bUpdate.setOnClickListener{ view: View ->
            updateProfile(database)
        }

        bSignOut.setOnClickListener{ view: View ->
            logOut()
        }

    }

    private fun logOut() {
        preferences.edit().putString("userName", "").apply()
        preferences.edit().putString("profession", "").apply()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun updateProfile(database: FirebaseDatabase) {
        if (!Helper.validateEmptyField(etProfileUserName)) {
            Toast.makeText(this, getString(R.string.enter_your_userName), Toast.LENGTH_SHORT).show()
            return
        }

        if (!Helper.validateEmptyField(etProfileProfession)) {
            Toast.makeText(this, getString(R.string.enter_your_profession), Toast.LENGTH_SHORT).show()
            return
        }

        var currentUserName = preferences.getString("userName", "")
        var currentProfession = preferences.getString("profession", "")

        if(currentUserName == etProfileUserName.text.toString()
            && currentProfession == etProfileProfession.text.toString()){
            return
        }

        val usersRef = database.getReference("users")
        if(currentUserName != etProfileUserName.text.toString()){
            val q = usersRef.orderByChild("userName").equalTo(etProfileUserName.text.toString())
            q.get().addOnSuccessListener {
                val us = it.getValue<Map<String, User>>()
                if(us != null && us.count() != 0){
                    Toast.makeText(this@ProfileActivity, R.string.userName_exists, Toast.LENGTH_SHORT).show()
                }else{
                    updateInfo(database, currentUserName)
                }
            }.addOnFailureListener{
                Toast.makeText(this@ProfileActivity, R.string.Unknown_error, Toast.LENGTH_SHORT).show()
            }
        }else{
            updateInfo(database, currentUserName)
        }

    }

    private fun updateInfo(database: FirebaseDatabase, currentUserName: String?) {
        val usersRef = database.getReference("users")
        Log.d("user", currentUserName.toString())
        val q = usersRef.orderByChild("userName").equalTo(currentUserName)
        q.get().addOnSuccessListener {
            val us = it.getValue<Map<String, User>>()
            val mp = us?.iterator()?.next()
            val key = mp?.key
            val value = mp?.value
            usersRef.child(key!!).setValue(
                User(key, etProfileUserName.text.toString(), value?.passwordHash,
                    etProfileProfession.text.toString())
            )
            preferences.edit().putString("userName", etProfileUserName.text.toString()).apply()
            preferences.edit().putString("profession", etProfileProfession.text.toString()).apply()

        }.addOnFailureListener{
            Toast.makeText(this@ProfileActivity, R.string.Unknown_error, Toast.LENGTH_SHORT).show()
        }
    }
}