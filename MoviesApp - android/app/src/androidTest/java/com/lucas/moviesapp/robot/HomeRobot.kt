package com.lucas.moviesapp.robot

import android.content.Context
import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.lucas.moviesapp.R
import com.lucas.moviesapp.service.POPULAR
import com.lucas.moviesapp.utils.MainUtils
import com.lucas.moviesapp.view.HomeActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.io.IOException
import java.io.InputStream

class HomeRobot(
    private val mockWebServer: MockWebServer?,
    private val activityTest: ActivityTestRule<HomeActivity>
) {
    fun setRequest(): HomeRobot {
        mockWebServer?.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                if (request.path?.contains(POPULAR) == true) {
                    return MockResponse().setBody(
                        readFileFromAssets(
                            "response.json",
                            InstrumentationRegistry.getInstrumentation().context
                        )
                    )
                }

                return MockResponse().setBody(
                    readFileFromAssets(
                        "error_not_found.json",
                        InstrumentationRegistry.getInstrumentation().context
                    )
                )
            }
        }

        return this
    }

    private fun readFileFromAssets(fileName: String, c: Context): String {
        return try {
            val `is`: InputStream = c.assets.open(fileName)
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun checkIsDisplayedRecyclerView(): HomeRobot{
        Thread.sleep(2000)
        MainUtils.checkRecyclerView(R.id.recyclerview)
        return this
    }

    fun checkScroll(): HomeRobot{
        MainUtils.scrollToRecyclerViewLastPosition(activityTest.activity, R.id.recyclerview)
        MainUtils.scroll(R.id.recyclerview, 11)
        Thread.sleep(3000)
        MainUtils.checkTextIsDisplayedOnRecyclerViewPosition(
            R.id.recyclerview,
            11,
            "The Doorman"
        )
        Thread.sleep(3000)

        return this
    }

    fun startActivity(): HomeRobot{
        activityTest.launchActivity(Intent(Intent.ACTION_MAIN))
        return this
    }

    fun finishActivity(){
        activityTest.finishActivity()
    }


}