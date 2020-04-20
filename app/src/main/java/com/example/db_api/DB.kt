package com.example.db_api

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class DB(val context: Context) {

    fun testPost() {
        val q = Volley.newRequestQueue(context)
        val url = "https://s5112101.bucomputing.uk/PlatePrepApi/index.php"
        val postRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                Log.d("Worked", "response is: ${response.toString()}")
            },
            Response.ErrorListener {
                Log.d("Failed", "Failed to connect")
            })
        q.add(postRequest)

    }

    fun insertNewUser(c: (response : String) -> Unit){
        var paramsh = HashMap<String,String>()
        paramsh["name"] = "mantas"
        val params = JSONObject()
        params.put("name","mantas")



        val q = Volley.newRequestQueue(context)
        val url = "https://s5112101.bucomputing.uk/PlatePrepApi/newUser.php"
        var postRequest = JsonObjectRequest(
            Request.Method.POST, url,
            params,
            Response.Listener { response ->
                Log.d("Worked", "response is: ${response.toString()}")
                c(response.toString())


            },
            Response.ErrorListener { error->
                Log.d("Failed", error.toString())
            })
        q.add(postRequest)
    }


}