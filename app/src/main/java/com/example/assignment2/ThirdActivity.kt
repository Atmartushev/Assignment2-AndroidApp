package com.example.assignment2

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.assignment2.ui.theme.Assignment2Theme

class ThirdActivity : ComponentActivity() {

    override fun onCreate(savedInstaceState: Bundle?) {
        super.onCreate(savedInstaceState)
        setContent{
            Assignment2Theme {
                ImageCaptureScreen {
                    val mainActivityIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainActivityIntent)
                }
            }
        }
        }
    }


@Composable
fun ImageCaptureScreen( navigateToMain: () -> Unit) {
    // State to store the captured image
    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }

    // Launch Camera
    val captureImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as? Bitmap
            capturedImage = imageBitmap
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center

    ) {
        // Button to Capture Image
        Button(onClick = {
            val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            captureImageLauncher.launch(intent)
        }) {
            Text(text = "Capture Image")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Display captured image

        capturedImage?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Captured Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
        Button(onClick = navigateToMain) {
            Text(text = "Back to Main Page")
        }
    }
}