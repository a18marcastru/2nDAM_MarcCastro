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
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _uiStateUser = MutableStateFlow(UserList())
    val uiStateUser: StateFlow<UserList> = _uiStateUser.asStateFlow()

    fun newUser(name: String, age: String, email: String, password: String) {
        val newUser = User(
            name = name,
            age = age.toInt(),
            email = email,
            password = password
        )

        viewModelScope.launch {
            repository.insertUser(newUser)

            try {
                val user = repository.getUserAll()
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }
}