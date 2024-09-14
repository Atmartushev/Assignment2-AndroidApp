package com.example.assignment2

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        startExplicitly = {
                            val explicitIntent = Intent(this, SecondActivity::class.java)
                            startActivity(explicitIntent)
                        },
                        startImplicitly = {
                            val implicitIntent = Intent("com.example.ACTION_VIEW")
                            startActivity(implicitIntent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    startExplicitly: () -> Unit,
    startImplicitly: () -> Unit
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
        }
    }
}
