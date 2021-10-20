package com.lucas.moviesapp.utils

import android.app.Activity
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*

object MainUtils {

    fun inputType(id: Int, text: String){
        onView(withId(id))
            .perform(typeText(text))
    }

    fun clickButton(id: Int){
        onView(withId(id))
            .perform(click())
    }

    fun hideKeyboard(){
        closeSoftKeyboard()
    }

    fun scroll(recyclerId: Int, pos: Int){
        onView(withId(recyclerId))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(pos))
    }

    fun withRecyclerView(recyclerId: Int): RecyclerViewMatchers{
        return RecyclerViewMatchers(recyclerId)
    }

    fun checkTextIsDisplayedOnRecyclerViewPosition(id: Int, pos: Int, text: String){
        onView(withRecyclerView(id).atPosition(pos))
            .check(matches(hasDescendant(withText(text))))
    }

    fun checkRecyclerView(recyclerId: Int){
        onView(withId(recyclerId))
            .check(matches(isDisplayed()))
    }


    fun scrollToRecyclerViewLastPosition(
        activity: Activity,
        @IdRes recyclerViewId: Int
    ) {
        val recyclerView = activity.findViewById<RecyclerView>(recyclerViewId)
        val itemCount = recyclerView.adapter?.itemCount as Int
        onView(withId(recyclerViewId))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))
    }

}