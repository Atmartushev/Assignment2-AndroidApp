package com.example.assignment2

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SecondActivityTest {

    private lateinit var device: UiDevice

    @Before
    fun setUp() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(androidx.test.platform.app.InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for the launcher
        val launcherPackage = getLauncherPackageName()
        device.wait(Until.hasObject(By.pkg(launcherPackage)), 5000)
    }

    @Test
    fun testExplicitIntentAndVerifyChallenge() {
        // Launch the app from the home screen
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage("com.example.assignment2")
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // Clear out any previous activities
        context.startActivity(intent)

        // Wait for the main activity to open
        device.wait(Until.hasObject(By.text("Start Activity Explicitly")), 5000)


        // Click the "Start Activity Explicitly" button
        val explicitButton = device.findObject(UiSelector().text("Start Activity Explicitly"))
        explicitButton.click()

        // Wait for the second activity to open
        device.wait(Until.hasObject(By.textContains("Battery Efficiency")), 5000)

        // Verify that one of the challenges is displayed
        val challengeText = device.findObject(UiSelector().textContains("Battery Efficiency"))
        assertTrue("Mobile software engineering challenge is displayed", challengeText.exists())
    }

    // Helper method to get the launcher package name
    private fun getLauncherPackageName(): String {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        val resolveInfo = context.packageManager.resolveActivity(intent, 0)
        return resolveInfo?.activityInfo?.packageName ?: "com.android.launcher"
    }
}
