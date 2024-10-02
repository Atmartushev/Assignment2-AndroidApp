package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment2.ui.theme.Assignment2Theme


class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                SecondScreen {
                    // Navigate back to MainActivity
                    val mainActivityIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainActivityIntent)
                }
            }
        }
    }
}


@Composable
fun SecondScreen(navigateToMain: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // List of mobile software engineering challenges
        Text(text = "1. Battery Efficiency")
        Text(text = "2. Different Screen Sizes")
        Text(text = "3. Network Capabilities")
        Text(text = "4. Security and Privacy")
        Text(text = "5. Resource Constraints")

        Spacer(modifier = Modifier.height(20.dp))

        // Button to go back to the main activity
        Button(onClick = navigateToMain) {
            Text(text = "Main Activity")
        }
    }
}
