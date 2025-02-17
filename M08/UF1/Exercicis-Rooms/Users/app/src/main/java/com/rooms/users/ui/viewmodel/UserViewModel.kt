package com.rooms.users.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rooms.users.data.model.User
import com.rooms.users.data.model.UserList
import com.rooms.users.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _uiStateUser = MutableStateFlow(UserList())
    val uiStateUser: StateFlow<UserList> = _uiStateUser.asStateFlow()

    init {
        viewModelScope.launch {
            // repository.deleteAll()
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

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = repository.getLogin(email, password, true)
                if(result > 0) {
                    val user = repository.getUser()
                    if(user != null) {
                        _uiStateUser.update { currentState ->
                            currentState.copy(userlist = user)
                        }
                    }
                }
                else Log.d("User", "No existe usuario")
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }

    fun newUser(name: String, age: String, email: String, password: String) {
        val newUser = User(
            name = name,
            age = age.toInt(),
            email = email,
            password = password,
            predetermined = true
        )

        viewModelScope.launch {
            try {
                repository.insertUser(newUser)
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

    fun logout(idUser: Int) {
        viewModelScope.launch {
            repository.updatePredetermined(idUser, false)
        }
    }

    fun deleteAccount(idUser: Int) {
        viewModelScope.launch {
            repository.deleteUser(idUser)
        }
    }
}