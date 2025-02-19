package com.examen.marc_castro_val2.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.marc_castro_val2.data.model.User
import com.examen.marc_castro_val2.data.model.UserList
import com.examen.marc_castro_val2.data.repository.Val2Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Val2ViewModel(private val repository: Val2Repository) : ViewModel() {
    private val _uiStateUser = MutableStateFlow(UserList())
    val uiStateUser: StateFlow<UserList> = _uiStateUser.asStateFlow()

    init {
        viewModelScope.launch {
            // repository.deleteAll() // Para eliminar los datos de la base de datos
            try {
                val user = repository.getUser()
                if(user != null) {
                    _uiStateUser.update { currentState ->
                        currentState.copy(userlist = user)
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }

    fun login(nombre: String, apellido: String) {
        val newUser = User(
            nombre = nombre,
            apellido = apellido
        )
        viewModelScope.launch {
            try {
                repository.insertUser(newUser)
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }
}