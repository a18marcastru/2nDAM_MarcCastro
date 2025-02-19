package com.examen.marc_castro_val2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.examen.marc_castro_val2.data.database.Val2DB
import com.examen.marc_castro_val2.data.repository.Val2Repository
import com.examen.marc_castro_val2.ui.Val2App
import com.examen.marc_castro_val2.ui.theme.Marc_Castro_Val2Theme
import com.examen.marc_castro_val2.ui.viewmodel.Val2ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            Val2DB::class.java,
            "Val2-Database"
        ).build()

        val dao = db.val2Dao()

        val repository = Val2Repository(dao)

        val viewModel = object : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return Val2ViewModel(repository) as T
            }
        }.create(Val2ViewModel::class.java)

        setContent {
            Marc_Castro_Val2Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Val2App(viewModel)
                }
            }
        }
    }
}