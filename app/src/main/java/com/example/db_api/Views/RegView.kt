package com.example.db_api.Views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.db_api.DB
import com.example.db_api.R
import org.w3c.dom.Text

class RegView : LinearLayout{





    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.register,this)
        setup()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setup(){
        val submitButton = findViewById<Button>(R.id.confirmButton)

        submitButton.setOnClickListener {
            val email = findViewById<TextView>(R.id.emailInput).text.toString()
            val password = findViewById<TextView>(R.id.passwordInput).text.toString()
            val retypepassword = findViewById<TextView>(R.id.confirmPasswordInput).text.toString()
            val fname = findViewById<TextView>(R.id.firstnameInput).text.toString()
            val sname = findViewById<TextView>(R.id.surnameInput).text.toString()

            val db = DB(this.context)
            db.insertNewUser(fname,sname,email,password,false){

            }


        }
    }
}