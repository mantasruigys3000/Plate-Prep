package com.example.db_api

import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.yeet.Model
import com.example.yeet.RecipeApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.searchbar.*
import kotlinx.android.synthetic.main.searchbar.view.*


class SearchActivity: AppCompatActivity() {

    //Api Stuff

    private var disposable: Disposable? = null

    private val RecipeApiCall by lazy {
        RecipeApiService.create()
    }

    private val ingredientList = mutableListOf<String>()

    //Autocomplete stuff

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searchbar)

        confirmButton.setOnClickListener {
            if (ingredientList.isNotEmpty()) {
                beginFilter(ingredientList.joinToString(separator = ","))
                // [chicken, dog,cat]  = "chicken,dog,cat"
            }
        }

        addButton.setOnClickListener {
            if (searchInput.text.toString().isNotEmpty()) {
                ingredientList.add(searchInput.text.toString())
                recipeObject.text = ingredientList.toString()
                searchInput.text.clear()
            }
        }
        removeButton.setOnClickListener {
            if (ingredientList.isNotEmpty())
                ingredientList.removeAt(ingredientList.size - 1)
            recipeObject.text = ingredientList.toString()
        }

    }

    private fun beginFilter(searchString: String) {
        //instance of interface being called
        disposable = RecipeApiCall.recipeFilter(searchString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result.meals !== null) {
                        resultsText.text = "${result.meals.size} recipes found"
                        displayResults(result)
                    } else {
                        resultsText.text = "No meals found"
                    }
                },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
            )
    }

    fun displayResults(result: Model.Result) {
        var t = mutableListOf<TextView>()
        val dim = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val dtv = dynamicTextViews
        //var i: Int = 0
        for (i in 0..result.meals.size) {
            if(result.meals[i].strMeal != null){
                t.add(i, TextView(this) )
                t[i].text = result.meals[i].strMeal
                dtv.addView(t[i])
            }

        }
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}