package com.rooms.users.data.repository

import com.rooms.users.data.dao.UserDao
import com.rooms.users.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val dao: UserDao) {
    suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {
            dao.insertUser(User(
                name = user.name,
                age = user.age,
                email = user.email,
                password = user.password,
                predetermined = user.predetermined
            ))
        }
    }

    suspend fun getUser() : List<User>? {
        return withContext(Dispatchers.IO) {
            dao.getUser()
        }
    }

    suspend fun getLogin(email: String, password: String, predetermined: Boolean) : Int {
         return withContext(Dispatchers.IO) {
            dao.getLogin(email, password, predetermined)
        }
    }

    suspend fun updatePredetermined(idUser: Int, predetermined: Boolean) {
        withContext(Dispatchers.IO) {
            dao.updatePredetermined(idUser, predetermined)
        }
    }

    suspend fun deleteUser(id: Int) {
        withContext(Dispatchers.IO) {
            dao.deleteUser(id)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            dao.deleteAll()
        }
    }
}