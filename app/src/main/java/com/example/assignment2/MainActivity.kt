package com.example.assignment2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        startExplicitly = {
                            if (checkAndRequestPermission()) {
                                val explicitIntent = Intent(this, SecondActivity::class.java)
                                startActivity(explicitIntent)
                            }
                        },
                        startImplicitly = {
                            val implicitIntent = Intent("com.example.ACTION_VIEW")
                            startActivity(implicitIntent)
                        },
                        viewImageActivity = {
                            val imageIntent = Intent(this, ThirdActivity::class.java)
                            startActivity(imageIntent)
                        }
                    )
                }
            }
        }
    }

    // Check and request custom permission at runtime
    private fun checkAndRequestPermission(): Boolean {
        val customPermission = "com.example.assignment2.MSE412"
        return if (ContextCompat.checkSelfPermission(this, customPermission) == PackageManager.PERMISSION_GRANTED) {
            // Permission already granted
            true
        } else {
            // Request the permission
            ActivityCompat.requestPermissions(this, arrayOf(customPermission), PERMISSION_REQUEST_CODE)
            false
        }
    }

    // Handle the result of the permission request
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start the second activity
                val explicitIntent = Intent(this, SecondActivity::class.java)
                startActivity(explicitIntent)
            } else {
                // Permission denied, show a message
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    startExplicitly: () -> Unit,
    startImplicitly: () -> Unit,
    viewImageActivity: () -> Unit
) {
    Surface(color = Color.Blue, modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {

            Text(text = "Avtonom Martushev", color = Color.White, fontSize = 25.sp)
            Text(text = "Student ID: 1353591", color = Color.White)

            Spacer(modifier = Modifier.height(20.dp))

            // Button to start the second activity explicitly
            Button(onClick = startExplicitly) {
                Text(text = "Start Activity Explicitly")
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Button to start the second activity implicitly
            Button(onClick = startImplicitly) {
                Text(text = "Start Activity Implicitly")
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Button to start the third activity (View Image Activity)
            Button(onClick = viewImageActivity) {
                Text(text = "View Image Activity")
            }
        }
    }
}
