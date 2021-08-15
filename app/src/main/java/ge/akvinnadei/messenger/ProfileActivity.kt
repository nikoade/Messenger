package ge.akvinnadei.messenger

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        setupView()
    }

    private fun setupView() {
        var userName = preferences.getString("userName", "")
        var profession = preferences.getString("profession", "")

        etProfileUserName.setText(userName)
        etProfileProfession.setText(profession)

        bHome.setOnClickListener { view: View ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        bUpdate.setOnClickListener{ view: View ->
            updateProfile()
        }

        bSignOut.setOnClickListener{ view: View ->
            preferences.edit().putString("userName", "")
            preferences.edit().putString("profession", "")
        }

    }

    private fun updateProfile() {
        if (!Helper.validateEmptyField(etProfileUserName)) {
            Toast.makeText(this, getString(R.string.enter_your_userName), Toast.LENGTH_SHORT).show()
            return
        }

        if (!Helper.validateEmptyField(etProfileProfession)) {
            Toast.makeText(this, getString(R.string.enter_your_userName), Toast.LENGTH_SHORT).show()
            return
        }

        var userName = etProfileUserName.text
        var profession = etProfileProfession.text

        // change username in database
        // change profession in database


    }
}