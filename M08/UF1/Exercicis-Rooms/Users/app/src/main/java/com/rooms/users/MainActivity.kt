package com.rooms.users

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.rooms.users.data.database.UserDB
import com.rooms.users.ui.theme.UsersTheme
import com.rooms.users.data.repository.UserRepository
import com.rooms.users.ui.UserApp
import com.rooms.users.ui.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            UserDB::class.java,
            "Users-Database-Exercici2"
        ).build()

        val dao = db.userDao()

        val repository = UserRepository(dao)

        val viewModel = object : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UserViewModel(repository) as T
            }
        }.create(UserViewModel::class.java)

        setContent {
            UsersTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    UserApp(viewModel)
                }
            }
        }
    }
}