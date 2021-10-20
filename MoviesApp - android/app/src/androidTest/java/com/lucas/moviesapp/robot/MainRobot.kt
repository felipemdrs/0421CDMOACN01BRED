package com.lucas.moviesapp.robot

import android.content.Intent
import androidx.test.rule.ActivityTestRule
import com.lucas.moviesapp.R
import com.lucas.moviesapp.utils.MainUtils
import com.lucas.moviesapp.utils.MainUtils.clickButton
import com.lucas.moviesapp.utils.MainUtils.inputType
import com.lucas.moviesapp.view.MainActivity
import okhttp3.internal.wait

class MainRobot(
    private val activityTestRule: ActivityTestRule<MainActivity>
) {

    fun startActivity(): MainRobot{
        activityTestRule.launchActivity(Intent(Intent.ACTION_MAIN))
        return this
    }

    fun finishActivity(){
        activityTestRule.finishActivity()
    }

    fun inputType(): MainRobot{
        inputType(R.id.etName, "Felipe")
        MainUtils.hideKeyboard()
        inputType(R.id.etProfissao, "dev backend")
        MainUtils.hideKeyboard()

        return this
    }

    fun clickButton() : MainRobot{
        clickButton(R.id.btnEntrar)
        return this
    }
}