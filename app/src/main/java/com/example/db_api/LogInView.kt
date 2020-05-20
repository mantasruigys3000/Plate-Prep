package com.example.db_api

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login.*



class LogInView: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        confirmButton.setOnClickListener{
            val db = DB(this)
            db.login(emailInput.text.toString(),passwordInput.text.toString()) {
                print(it.toString())
                val intent = Intent(this, SearchActivity::class.java).apply{
                    putExtra("Name", it.getString("name"))
                }
                startActivity(intent)
            }
        }

    }

}