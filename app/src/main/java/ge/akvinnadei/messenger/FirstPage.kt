package ge.akvinnadei.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.buttons.*

class FirstPage : AppCompatActivity() {
    lateinit var button_signup: Button
    lateinit var userName: EditText
    lateinit var password: EditText
    lateinit var profession: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buttons)
        //test
        setUpButtons()
    }

    private fun setUpButtons() {
        bMainPage.setOnClickListener {  view: View ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        bSearchPage.setOnClickListener {  view: View ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        bProfilePage.setOnClickListener {  view: View ->
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        bLoginPage.setOnClickListener {  view: View ->
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}