package com.example.aimusicvideoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aimusicvideoapp.ui.theme.AIMusicVideoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // File picker launcher
        val pickAudio = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri -> selectedAudioUri = uri }

        setContent {
            AIMusicVideoAppTheme {
                var prompt by remember { mutableStateOf("") }
                var status by remember { mutableStateOf("Idle") }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        OutlinedTextField(
                            value = prompt,
                            onValueChange = { prompt = it },
                            label = { Text("Enter video prompt") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            onClick = { pickAudio.launch("audio/*") },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Choose Audio File")
                        }

                        Button(
                            onClick = {
                                status = "Starting generation..."
                                // AI call will be added later
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Generate AI Music Video")
                        }

                        Text(text = "Status: $status")
                    }
                }
            }
        }
    }

    companion object {
        var selectedAudioUri: android.net.Uri? = null
    }
}
