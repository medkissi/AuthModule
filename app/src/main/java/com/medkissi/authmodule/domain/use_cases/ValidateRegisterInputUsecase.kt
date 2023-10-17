package com.medkissi.authmodule.domain.use_cases

import com.medkissi.authmodule.domain.model.RegisterInputValidationType
import com.medkissi.authmodule.utils.containsNumber
import com.medkissi.authmodule.utils.containsSpecialChar
import com.medkissi.authmodule.utils.containsUpperCase
import javax.inject.Inject

class ValidateRegisterInputUsecase () {
    operator fun invoke(
        email:String,
        password:String,
        passwordRepeated:String
        ):RegisterInputValidationType{
        if(email.isEmpty() || password.isEmpty() || passwordRepeated.isEmpty()){
            return RegisterInputValidationType.EmptyField
        }
        if ("@" !in email){
            return  RegisterInputValidationType.NoEmail

        }
        if (password != passwordRepeated){
            return RegisterInputValidationType.PasswordsDoNotMatch
        }
        if (password.count() < 8 ){
            return RegisterInputValidationType.PasswordTooShort

        }
        if (!password.containsSpecialChar()){
            return RegisterInputValidationType.PasswordSpecialCarMissing

        }
        if (!password.containsNumber()){
            return RegisterInputValidationType.PasswordNumberMissing
        }
        if (!password.containsUpperCase()){
            return RegisterInputValidationType.PasswordUpperCaseMissing
        }

        return  RegisterInputValidationType.Valid
    }
}