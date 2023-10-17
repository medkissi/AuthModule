package com.medkissi.authmodule.domain.use_cases

import com.google.common.truth.Truth.assertThat
import com.medkissi.authmodule.domain.model.RegisterInputValidationType
import org.junit.Test

class ValidateRegisterInputUsecaseTest{
    private val validateRegisterInputUseCase = ValidateRegisterInputUsecase()

    @Test
    fun `test register input with correct email and password returns valid`(){
        val result = validateRegisterInputUseCase("papiscamara@2gmail.com","Test12345`","Test12345`")
        assertThat(result).isEqualTo(RegisterInputValidationType.Valid)
    }

    @Test
    fun `test register input with no email gives correct return type`(){
        val result = validateRegisterInputUseCase("papiscamara.gmail.com","Test12345`","Test12345`")
        assertThat(result).isEqualTo(RegisterInputValidationType.NoEmail)
    }

    @Test
    fun `test register input with not matching password gives correct return type`(){
        val result = validateRegisterInputUseCase("papiscamara@2gmail.com","Test1234`","Test12345`")
        assertThat(result).isEqualTo(RegisterInputValidationType.PasswordsDoNotMatch)
    }


    @Test
    fun `test register input with  upper case missing password  gives correct return type`(){
        val result = validateRegisterInputUseCase("papiscamara@2gmail.com","test1234`","test1234`")
        assertThat(result).isEqualTo(RegisterInputValidationType.PasswordUpperCaseMissing)
    }


    @Test
    fun `test register input with number missing  password gives correct return type`(){
        val result = validateRegisterInputUseCase("papiscamara@2gmail.com","Testpasser`","Testpasser`")
        assertThat(result).isEqualTo(RegisterInputValidationType.PasswordNumberMissing)
    }

    @Test
    fun `test register special caracter missing password  gives correct return type`(){
        val result = validateRegisterInputUseCase("papiscamara@2gmail.com","test1234","test1234")
        assertThat(result).isEqualTo(RegisterInputValidationType.PasswordSpecialCarMissing)
    }
    @Test
    fun `test register input with password too short  gives correct return type`(){
        val result = validateRegisterInputUseCase("papiscamara@2gmail.com","Test12`","Test12`")
        assertThat(result).isEqualTo(RegisterInputValidationType.PasswordTooShort)
    }

}