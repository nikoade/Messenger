package ge.akvinnadei.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupView()
    }

    private fun setupView() {
        bLogin.setOnClickListener {  view: View ->
            login()
        }

        bSignUp2.setOnClickListener {  view: View ->
            register()
        }

        bSignUp1.setOnClickListener {  view: View ->
            clLogin.visibility= View.GONE;
            clRegister.visibility= View.VISIBLE;
        }
    }

    private fun register(){
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