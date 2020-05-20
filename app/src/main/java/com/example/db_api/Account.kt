package com.example.db_api

import android.content.Context

class Account (
        val context :Context,
        val id : Int ,
        val Name: String){

    var favList = mutableListOf<Int>()


    fun updateFavs(){
        val db = DB(context)
    }
}