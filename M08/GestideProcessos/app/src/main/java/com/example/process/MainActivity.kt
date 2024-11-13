package com.example.process

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.process.ui.ProcessApp
import com.example.process.ui.theme.GestióDeProcessosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestióDeProcessosTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    ProcessApp()
                }
            }
        }
    }
}