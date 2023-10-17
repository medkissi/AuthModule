package com.medkissi.authmodule.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medkissi.authmodule.domain.model.LoginInputValidationType
import com.medkissi.authmodule.domain.model.LoginInputValidationType.*
import com.medkissi.authmodule.domain.repository.AuthRepository
import com.medkissi.authmodule.domain.use_cases.ValidateLoginInputUseCase
import com.medkissi.authmodule.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val authRepository: AuthRepository,
) : ViewModel() {

    var loginState by mutableStateOf(LoginState())
        private set

    fun onEmailInputChange(newValue: String) {
        loginState = loginState.copy(emailInput = newValue)
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue: String) {
        loginState = loginState.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onToggleVisualTranformation() {
        loginState = loginState.copy(isPasswordShowm = !loginState.isPasswordShowm)
    }

    fun onLoginClick() {
        loginState = loginState.copy(isLoading = true)
      viewModelScope.launch {
          loginState = try {
              val loginResult = authRepository.login(
                  loginState.emailInput,
                  loginState.passwordInput
              )
              loginState.copy(isSuccessfullyLoggedIn = loginResult)
          }catch (e:Exception){
              loginState.copy(errorMessageLoginProcess = "Could not login")
          }finally {
              loginState = loginState.copy(isLoading = false)
          }
      }

    }

    private fun checkInputValidation() {
        val validationResult = validateLoginInputUseCase(
            loginState.emailInput,
            loginState.passwordInput
        )
        processInputValidationType(validationResult)

    }

    private fun processInputValidationType(type: LoginInputValidationType) {
        loginState = when (type) {

            EmptyField -> {
                loginState.copy(errorMessageInput = "Empty fields left", isInputValid = false)

            }

            NoEmail -> {
                loginState.copy(errorMessageInput = "No valid email ", isInputValid = false)

            }
            Valid -> {
                loginState.copy(
                    errorMessageInput = null,
                    isInputValid = true
                )
            }
        }
    }

}