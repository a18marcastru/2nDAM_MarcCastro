package com.example.microservicesmanager.data.repository

import com.example.microservicesmanager.data.dao.ProfileDao
import com.example.microservicesmanager.data.model.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository(private val dao: ProfileDao) {

    suspend fun insertProfile(profile: Profile) {
        withContext(Dispatchers.IO) {
            dao.insertProfile(Profile(
                label = profile.label,
                color = profile.color,
                host = profile.host,
                port = profile.port,
                predetermined = profile.predetermined
            ))
        }
    }

    suspend fun getProfilesAll(): List<Profile>? {
        return withContext(Dispatchers.IO) {
            dao.getProfilesAll()
        }
    }

    suspend fun clearAllPredetermined(reset: Int) {
        withContext(Dispatchers.IO) {
            dao.clearAllPredetermined(reset)
        }
    }

    suspend fun updatePredetermined(profileId: Int, predetermined: Int) {
        withContext(Dispatchers.IO) {
            dao.updatePredetermined(profileId, predetermined)
        }
    }

    suspend fun updateProfile(idProfile: Int, label: String, colorHex: String, host: String, port: Int) {
        withContext(Dispatchers.IO) {
            dao.updateProfile(idProfile, label, colorHex, host, port)
        }
    }

    suspend fun deleteProfile(id: Int) {
        return withContext(Dispatchers.IO) {
            dao.deleteProfile(id)
        }
    }
}