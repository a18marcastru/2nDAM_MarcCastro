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
                surname = user.surname,
                age = user.age,
                email = user.email,
                password = user.password
            ))
        }
    }

    suspend fun getUserAll() :User? {
        withContext(Dispatchers.IO) {
            dao.getUserAll()
        }
    }

//    suspend fun getUser(email: String, password: String) :User? {
//        return withContext(Dispatchers.IO) {
//            dao.getUser(email, password)
//        }
//    }

//    suspend fun deleteNote(id: Int) {
//        withContext(Dispatchers.IO) {
//            dao.deleteNote(id)
//        }
//    }
}