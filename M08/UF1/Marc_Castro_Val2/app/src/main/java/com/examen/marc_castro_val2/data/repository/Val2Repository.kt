package com.examen.marc_castro_val2.data.repository

import com.examen.marc_castro_val2.data.dao.Val2Dao
import com.examen.marc_castro_val2.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Val2Repository(private val dao: Val2Dao) {
    suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {
            dao.insertUser(User(
                nombre = user.nombre,
                apellido = user.apellido
            ))
        }
    }

    suspend fun getUser() : List<User>? {
        return withContext(Dispatchers.IO) {
            dao.getUser()
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            dao.deleteAll()
        }
    }
}