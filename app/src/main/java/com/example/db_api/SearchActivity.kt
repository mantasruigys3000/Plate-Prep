package com.example.db_api

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
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


class SearchActivity : AppCompatActivity() {


    //Api Stuff

    private var disposable: Disposable? = null

    private val RecipeApiCall by lazy {
        RecipeApiService.create()
    }

    private val ingredientList = mutableListOf<String>()

    //Autocomplete stuff


    @RequiresApi(Build.VERSION_CODES.P)
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

    @RequiresApi(Build.VERSION_CODES.P)
    private fun beginFilter(searchString: String) {
        //instance of interface being called
        disposable = RecipeApiCall.recipeFilter(searchString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result.meals !== null) {
                        recipeObject.text = "${result.meals.size} recipes found"
                        displayResults(result)
                    } else {
                        recipeObject.text = "No meals found"
                    }
                },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
            )
    }


    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.P)
    fun displayResults(result: Model.Result) {
        var t = mutableListOf<TextView>()
//        val dim = LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.WRAP_CONTENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT
//        )
        val dtv = dynamicTextViews
        val layoutDefault = recipeObject2.layoutParams
        val fontVariation = recipeObject2.fontVariationSettings
        val fontSize = recipeObject2.textMetricsParams
        val padding = recipeObject2.paddingLeft
        val gravity = recipeObject2.gravity
        //var i: Int = 0
        for (i in 0..result.meals.size) {
            if (result.meals[i].strMeal != null) {
                t.add(i, TextView(this))
                t[i].text = result.meals[i].strMeal
                t[i].setBackgroundResource(R.color.orange)
                t[i].layoutParams = layoutDefault
                t[i].textMetricsParams = fontSize
                t[i].gravity = gravity
                t[i].setPadding(padding, 0, 0, 0)
                t[i].alpha = (1F)
                t[i].fontVariationSettings = fontVariation
                t[i].setOnClickListener {
                    val intent = Intent(this, RecipeActivity::class.java).apply {
                        putExtra("idMeal", result.meals[i].idMeal)
                    }
                    startActivity(intent)
                }
                dtv.addView(t[i])
            }

        }
    }



    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}