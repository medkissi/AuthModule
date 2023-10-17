package com.medkissi.authmodule.domain.use_cases

import com.google.common.truth.Truth.assertThat
import com.medkissi.authmodule.domain.model.LoginInputValidationType
import org.junit.Test

class ValidateLoginInputUsecaseTest {
   private val validateLoginInputUseCase = ValidateLoginInputUseCase()

    @Test
    fun `test empty email returns validation type empty field`(){

        val actual = validateLoginInputUseCase(email = "", password = "Password")
        assertThat(actual).isEqualTo(LoginInputValidationType.EmptyField)
    }
    @Test
    fun `test empty password returns validation type empty field`(){

        val actual = validateLoginInputUseCase(email = "papiscamara2@gmail.com", password = "")
        assertThat(actual).isEqualTo(LoginInputValidationType.EmptyField)
    }
    @Test
    fun `test incorrect email returns validation type  No email`(){

        val actual = validateLoginInputUseCase(email = "papiscamara2gmail.com", password = "Password")
        assertThat(actual).isEqualTo(LoginInputValidationType.NoEmail)
    }
    @Test
    fun `test  returns validation type valid `(){

        val actual = validateLoginInputUseCase(email = "papiscamara2@gmail.com", password = "Password")
        assertThat(actual).isEqualTo(LoginInputValidationType.Valid)
    }


}