package com.medkissi.authmodule.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medkissi.authmodule.domain.model.RegisterInputValidationType
import com.medkissi.authmodule.domain.repository.AuthRepository
import com.medkissi.authmodule.domain.use_cases.ValidateRegisterInputUsecase
import com.medkissi.authmodule.presentation.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerInputUsecase: ValidateRegisterInputUsecase,
    private val authRepository: AuthRepository
):ViewModel() {
    var registerState by mutableStateOf(RegisterState())
        private set

    fun onEmailInputChange(newValue:String){
        registerState = registerState.copy(emailInput = newValue)
        checkInputValidation()
    }
    fun onPasswordInputChange(newValue:String){
        registerState = registerState.copy(passwordInput = newValue)
        checkInputValidation()
    }
    fun onPasswordRepeatedInputChange(newValue:String){
        registerState = registerState.copy(passwordRepeatedInput = newValue)
        checkInputValidation()
    }

    fun onRegisterClick(){
        registerState = registerState.copy(isLoading = true)
        viewModelScope.launch {
            registerState = try{
                val registerResult = authRepository.register(
                registerState.emailInput,
                registerState.passwordInput
            )
                registerState.copy(isSuccessfullyRegistered = registerResult)

            }catch (e:Exception){
                registerState.copy(errorMessageRegisterProcess = "Could not register ")
            }
            finally {
                registerState = registerState.copy(isLoading = false)
            }

        }

    }



    fun onToggleVisualTranformationPassword(){
        registerState = registerState.copy(isPasswordShown = !registerState.isPasswordShown)
    }
    fun onToggleVisualTranformationPasswordRepeated(){
        registerState = registerState.copy(isPasswordRepeatedShown = !registerState.isPasswordRepeatedShown)
    }
    private fun checkInputValidation() {
        val validationResult= registerInputUsecase(
            registerState.emailInput,
            registerState.passwordInput,
            registerState.passwordRepeatedInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: RegisterInputValidationType) {
        registerState = when(type){
            RegisterInputValidationType.EmptyField -> {
                registerState.copy(errorMessageInput = "Empty fieds left", isInputValid = false)
            }
            RegisterInputValidationType.NoEmail -> {
                registerState.copy(errorMessageInput = "No valid email", isInputValid = false)
            }
            RegisterInputValidationType.PasswordsDoNotMatch -> {
                registerState.copy(errorMessageInput = "Passwords do not match", isInputValid = false)

            }
            RegisterInputValidationType.PasswordUpperCaseMissing -> {
                registerState.copy(
                    errorMessageInput = "Password must contain an upper case ",
                    isInputValid = false
                )

            }
            RegisterInputValidationType.PasswordNumberMissing -> {
                registerState.copy(
                    errorMessageInput = "Password needs to contain a number",
                    isInputValid = false
                )

            }
            RegisterInputValidationType.PasswordSpecialCarMissing ->{
                registerState.copy(
                    errorMessageInput = "Password needs to contain a special caracter",
                    isInputValid = false,
                )
            }
            RegisterInputValidationType.PasswordTooShort -> {
                registerState.copy(
                    errorMessageInput = "Password too short",
                    isInputValid = false
                )
            }
            RegisterInputValidationType.Valid -> {
                registerState.copy(
                    errorMessageInput = null,
                    isInputValid = true
                )
            }
        }
    }


}