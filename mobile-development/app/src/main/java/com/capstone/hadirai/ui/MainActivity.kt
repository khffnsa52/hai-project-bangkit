package com.capstone.hadirai.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.capstone.hadirai.ui.theme.HadirAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HadirAITheme {
                HadirAI()
            }
        }
    }
}