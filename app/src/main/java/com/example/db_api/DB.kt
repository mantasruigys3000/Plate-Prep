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


    fun insertNewUser(
        fname: String,
        sname : String,
        email: String,
        password : String,
        isPrem: Boolean,
        c: (response : String) -> Unit
    ){
        var params = JSONObject()
        params.put("fname",fname)
        params.put("sname",sname)
        params.put("email",email)
        params.put("password",password)
        params.put("isPrem",isPrem.toString())
        //val params = JSONObject()
        //params.put("name","mantas")



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

     fun addMeal(mealId : Int, mealName : String, c: (response : String) -> Unit){
        var params = JSONObject()
        params.put("id",mealId.toString())
        params.put("name",mealName)


        val q = Volley.newRequestQueue(context)
        val url = "https://s5112101.bucomputing.uk/PlatePrepApi/newMeal.php"
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

    fun login(email:String,password:String, c: (response: String) -> Unit){
        var params = JSONObject()
        params.put("email",email)
        params.put("password",password)


        val q = Volley.newRequestQueue(context)
        val url = "https://s5112101.bucomputing.uk/PlatePrepApi/login.php"
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