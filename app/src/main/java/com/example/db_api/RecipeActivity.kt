package com.example.db_api

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yeet.Model
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

        backButton.setOnClickListener(){
            onBackPressed()
        }



    }

    private fun beginLookup(searchString: String) {
        //instance of interface being called
        disposable = RecipeApiCall.mealLookup(searchString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result.meals != null) {
                        updateView(result.meals[0])
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

    private fun updateView(meal: Model.Meals) {
        recipeName.text = meal.strMeal.toString()
        descriptionView.text = meal.strArea
        instructionView.text = meal.strInstructions
        if(meal.strYoutube != null) {

        }
    }
}