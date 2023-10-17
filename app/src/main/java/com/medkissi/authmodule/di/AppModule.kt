package com.medkissi.authmodule.di

import com.medkissi.authmodule.data.repository.AuthRepositoryImpl
import com.medkissi.authmodule.domain.repository.AuthRepository
import com.medkissi.authmodule.domain.use_cases.ValidateLoginInputUseCase
import com.medkissi.authmodule.domain.use_cases.ValidateRegisterInputUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesValidateLoginInputUseCase():ValidateLoginInputUseCase = ValidateLoginInputUseCase()

    @Singleton
    @Provides
    fun providesRegisterInputUseCase():ValidateRegisterInputUsecase= ValidateRegisterInputUsecase()

    @Singleton
    @Provides
    fun providesAuthRepository():AuthRepository = AuthRepositoryImpl()
}
