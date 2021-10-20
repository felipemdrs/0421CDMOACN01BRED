package com.lucas.moviesapp.view

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.lucas.moviesapp.robot.HomeRobot
import com.lucas.moviesapp.service.MovieService
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{
    private lateinit var robot: HomeRobot
    private var mockWebServer: MockWebServer? = null

    init {
        mockWebServer = MockWebServer()
        MovieService.setBaseUrl(mockWebServer?.url("/").toString())
    }

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<HomeActivity>
    = ActivityScenarioRule(HomeActivity::class.java)


    @Before
    fun setup(){
        val scenario = activityScenarioRule.scenario
        robot = HomeRobot(mockWebServer, scenario)
    }

    @Test
    fun testNavigateListMovies(){
        robot
            .setRequest()
            .startActivity()
            .checkIsDisplayedRecyclerView()
    }

    @Test
    fun testScrollList(){
        robot.setRequest()
            .startActivity()
            .checkScroll()
    }

    @After
    fun tearDown(){
        mockWebServer?.shutdown()
        robot.finishActivity()
    }
}