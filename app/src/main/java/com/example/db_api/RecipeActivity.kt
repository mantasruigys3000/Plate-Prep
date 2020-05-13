package com.example.db_api

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yeet.RecipeApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.recipe.*


class RecipeActivity: AppCompatActivity() {

    private var disposable: Disposable? = null

    private val RecipeApiCall by lazy {
        RecipeApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe)
        displayResults()



    }

    private fun beginLookup(searchString: String) {
        //instance of interface being called
        disposable = RecipeApiCall.mealLookup(searchString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result.meals != null) {
                        recipeName.text = result.meals[0].strMeal.toString()
                        //(result)
                    } else {
                        recipeName.text = "Error"
                    }
                },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
            )
    }

    private fun displayResults() {
        val name = intent.getIntExtra("idMeal", 1)
        println(name)
        if(name != null) {
            beginLookup(name.toString())
        } else {
            Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show()
        }

    }
}