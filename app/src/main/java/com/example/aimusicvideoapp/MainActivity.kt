package com.example.aimusicvideoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.aimusicvideoapp.ui.theme.AIMusicVideoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AIMusicVideoTheme {
                AppUI(
                    onGenerate = { prompt ->
                        lifecycleScope.launch {
                            VideoGenerator.generate(prompt)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun AppUI(onGenerate: (String) -> Unit) {
    var prompt by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Music Video App") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {

            OutlinedTextField(
                value = prompt,
                onValueChange = { prompt = it },
                label = { Text("Enter a video prompt") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    isLoading = true
                    result = ""
                    onGenerate(prompt)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Generate Video")
            }

            Spacer(modifier = Modifier.height(30.dp))

            if (isLoading) {
                CircularProgressIndicator()
            }

            if (result.isNotEmpty()) {
                Text(result)
            }
        }
    }
}
