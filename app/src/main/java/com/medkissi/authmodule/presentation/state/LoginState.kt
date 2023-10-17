package com.medkissi.authmodule.presentation.state

data class LoginState(
    val emailInput:String ="",
    val passwordInput:String="",
    val isInputValid:Boolean=false,
    val isPasswordShowm:Boolean = false,
    val errorMessageInput:String?= null,
    val isLoading:Boolean =false,
    val isSuccessfullyLoggedIn:Boolean =false,
    val errorMessageLoginProcess:String?=null
)
