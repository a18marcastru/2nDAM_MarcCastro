package com.example.microservicesmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.microservicesmanager.data.database.MicroserviceDB
import com.example.microservicesmanager.data.repository.ProfileRepository
import com.example.microservicesmanager.ui.MicroserviceApp
import com.example.microservicesmanager.ui.theme.MicroservicesManagerTheme
import com.example.microservicesmanager.ui.viewmodel.MicroserviceViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            MicroserviceDB::class.java,
            "my_database"
        ).build()
        val dao = db.profileDao()

        val repository = ProfileRepository(dao)

        val viewModel = object : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MicroserviceViewModel(repository) as T
            }
        }.create(MicroserviceViewModel::class.java)

        setContent {
            MicroservicesManagerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MicroserviceApp(viewModel)
                }
            }
        }
    }
}

