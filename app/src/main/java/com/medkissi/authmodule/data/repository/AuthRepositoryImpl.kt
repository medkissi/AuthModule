package com.medkissi.authmodule.data.repository

import com.medkissi.authmodule.domain.repository.AuthRepository
import kotlinx.coroutines.delay

class AuthRepositoryImpl: AuthRepository {
    override suspend fun login(email: String, password: String): Boolean {
        delay(1000L)
        return true
    }

    override suspend fun register(email: String, password: String): Boolean {
        delay(1000L)
        return true
    }
}