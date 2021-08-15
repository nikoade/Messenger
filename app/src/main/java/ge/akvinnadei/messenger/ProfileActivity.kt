package ge.akvinnadei.messenger

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity  : AppCompatActivity() {

    private lateinit var preferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        preferences = getPreferences(MODE_PRIVATE)
        setupView()
    }

    private fun setupView() {
        var userName = "Abdul"// preferences.getSting("userName")
        var profession = "Shahil"//preferences.getString("profession")

        etProfileUserName.setText(userName)
        etProfileProfession.setText(profession)

        bUpdate.setOnClickListener{ view: View ->
            updateProfile()
        }
        bSignOut.setOnClickListener{ view: View ->
            //preferences.edit().putString("userName", "")
            //preferences.edit().putString("profession", "")
        }

    }

    private fun updateProfile() {
        if (!Helper.validateEmptyField(etProfileUserName)) {
            Toast.makeText(this, getString(R.string.enter_your_nickname), Toast.LENGTH_SHORT).show()
            return
        }

        if (!Helper.validateEmptyField(etProfileProfession)) {
            Toast.makeText(this, getString(R.string.enter_your_nickname), Toast.LENGTH_SHORT).show()
            return
        }

        var userName = etProfileUserName.text
        var profession = etProfileProfession.text

        // change username in database
        // change profession in database


    }
}