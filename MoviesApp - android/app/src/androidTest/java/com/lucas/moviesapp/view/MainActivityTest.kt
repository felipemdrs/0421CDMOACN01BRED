package com.lucas.moviesapp.view

import androidx.test.rule.ActivityTestRule
import com.lucas.moviesapp.robot.MainRobot
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{
    private lateinit var robot: MainRobot


    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setup(){
        robot = MainRobot(activityRule)
    }

    @Test
    fun testInputType(){
        robot
            .startActivity()
            .inputType()
    }

    @Test
    fun testClickButton(){
        robot
            .startActivity()
            .clickButton()
    }

    @Test
    fun testInputTypesAndClickButton(){
        robot
            .startActivity()
            .inputType()
            .clickButton()
    }

    @After
    fun tearDown(){
        robot.finishActivity()
    }
}