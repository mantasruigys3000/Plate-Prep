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


class RecipeActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    private val RecipeApiCall by lazy {
        RecipeApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe)
        displayResults()

        backButton.setOnClickListener() {
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
        if (name != null) {
            beginLookup(name.toString())
        } else {
            Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show()
        }

    }

    private fun updateView(meal: Model.Meals) {

        val ingredientList = mutableListOf<String?>()
        val tagList = mutableListOf<String?>()

        var emptyString: String = ""
        var emptyTagString = ""

        tagList.add(meal.strArea)
        tagList.add(meal.strCategory)
        tagList.add(meal.strTags)



        ingredientList.add(meal.strIngredient1 +meal.strMeasure1)
        ingredientList.add(meal.strIngredient2 +meal.strMeasure2)
        ingredientList.add(meal.strIngredient3 +meal.strMeasure3)
        ingredientList.add(meal.strIngredient4 +meal.strMeasure4)
        ingredientList.add(meal.strIngredient5 + meal.strMeasure5)
        ingredientList.add(meal.strIngredient6 +meal.strMeasure6)
        ingredientList.add(meal.strIngredient7 +meal.strMeasure7)
        ingredientList.add(meal.strIngredient8 +meal.strMeasure8)
        ingredientList.add(meal.strIngredient9 +meal.strMeasure9)
        ingredientList.add(meal.strIngredient10 +meal.strMeasure10)
        ingredientList.add(meal.strIngredient11 +meal.strMeasure11)
        ingredientList.add(meal.strIngredient12 +meal.strMeasure12)
        ingredientList.add(meal.strIngredient13 + meal.strMeasure13)
        ingredientList.add(meal.strIngredient14 +meal.strMeasure14)
        ingredientList.add(meal.strIngredient15 +meal.strMeasure15)
        ingredientList.add(meal.strIngredient16 +meal.strMeasure16)
        ingredientList.add(meal.strIngredient17 +meal.strMeasure17)
        ingredientList.add(meal.strIngredient18 +meal.strMeasure18)
        ingredientList.add(meal.strIngredient19 +meal.strMeasure19)
        ingredientList.add(meal.strIngredient20 +meal.strMeasure20)


        for (item in ingredientList) {
            if (!item.isNullOrEmpty()) {
                emptyString += "$item \n"
            }
        }

        for (item in tagList) {
            if (!item.isNullOrEmpty()) {
                emptyTagString += " $item ,"
            }
        }

        recipeName.text = meal.strMeal.toString()

        ingredientView.text = emptyString
        stepView.text = meal.strInstructions
        descriptionView.text = emptyTagString

        if (meal.strYoutube != null) {

        }
    }
}
