package com.example.aimusicvideoapp

import kotlinx.coroutines.delay

object VideoGenerator {

    suspend fun generate(aiPrompt: String): String {
        // Simulate AI work
        delay(3000)
        return "Generated video using prompt: $aiPrompt"
    }
}
