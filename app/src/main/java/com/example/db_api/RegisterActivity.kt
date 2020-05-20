package com.example.db_api

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login.*

class RegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        confirmButton.setOnClickListener{
            setup()
        }
    }


    fun setup() {


            val email = findViewById<TextView>(R.id.emailInput).text.toString()
            val password = findViewById<TextView>(R.id.passwordInput).text.toString()
            val retypepassword = findViewById<TextView>(R.id.confirmPasswordInput).text.toString()
            val fname = findViewById<TextView>(R.id.firstnameInput).text.toString()
            val sname = findViewById<TextView>(R.id.surnameInput).text.toString()

            val db = DB(this)
            db.insertNewUser(fname, sname, email, password, false) {
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)



    }
}