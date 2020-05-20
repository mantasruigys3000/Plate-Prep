package com.example.db_api

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.db_api.Views.RegView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //var regButton = Button(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val regButton = findViewById<Button>(R.id.button4)

        logInButton.setOnClickListener{
            val loginIntent = Intent(this, LogInView::class.java)
            startActivity(loginIntent)
        }

        regButton.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }




    fun startIntent(view: View) {

        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)


    }


}
