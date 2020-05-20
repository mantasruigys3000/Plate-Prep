package com.example.db_api.Views

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.get
import com.example.db_api.DB
import com.example.db_api.MainActivity
import com.example.db_api.R

class RegView : LinearLayout {
    var a = Activity()


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.register, this)
        setup()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setup() {
        val submitButton = findViewById<Button>(R.id.confirmButton)

        submitButton.setOnClickListener {
            val email = findViewById<TextView>(R.id.searchInput).text.toString()
            val password = findViewById<TextView>(R.id.passwordInput).text.toString()
            val retypepassword = findViewById<TextView>(R.id.confirmPasswordInput).text.toString()
            val fname = findViewById<TextView>(R.id.firstnameInput).text.toString()
            val sname = findViewById<TextView>(R.id.surnameInput).text.toString()

            val db = DB(this.context)
            db.insertNewUser(fname, sname, email, password, false) {
            }
            a.setContentView(R.layout.activity_main)


        }
    }
}