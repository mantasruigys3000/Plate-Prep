package com.example.db_api

import android.content.Context

class Account (
        val context :Context,
        val id : Int ,
        val Name: String){


    fun addFav (mealId: Int){
        val db = DB(context)

    }
}