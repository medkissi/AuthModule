package com.medkissi.authmodule.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.medkissi.authmodule.presentation.components.AuthButton
import com.medkissi.authmodule.presentation.components.BubbleAnimation
import com.medkissi.authmodule.presentation.components.HeaderBackground
import com.medkissi.authmodule.presentation.components.TextEntryModule
import com.medkissi.authmodule.presentation.ui.theme.gray
import com.medkissi.authmodule.presentation.ui.theme.orange
import com.medkissi.authmodule.presentation.ui.theme.white
import com.medkissi.authmodule.presentation.ui.theme.whiteGray
import com.medkissi.authmodule.presentation.ui.theme.whiteGrayOrange
import com.medkissi.authmodule.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSuccessNavigation: () -> Unit,
    onNavigationToRegisterScreen: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()

) {
    NavigationHelper(shouldNavigate = { loginViewModel.loginState.isSuccessfullyLoggedIn }) {
        onLoginSuccessNavigation()

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ) {
            HeaderBackground(
                leftColor = orange,
                rightColor = whiteGrayOrange,
                modifier = Modifier.fillMaxSize()
            )

            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
                color = white
            )

        }
        LoginScreenContainer(
            emailValue = { loginViewModel.loginState.emailInput },
            passwordValue = { loginViewModel.loginState.passwordInput },
            buttonEnabled = loginViewModel.loginState.isInputValid,
            onEmailChanged = loginViewModel::onEmailInputChange,
            onPasswordChanged = loginViewModel::onPasswordInputChange,
            onLoginButtonClick = loginViewModel::onLoginClick,
            isPasswordShown = { loginViewModel.loginState.isPasswordShowm },
            onTrailingPasswordIconClick = loginViewModel::onToggleVisualTranformation,
            errorHint = { loginViewModel.loginState.errorMessageInput },
            isLoading = { loginViewModel.loginState.isLoading },
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxWidth(0.9f)
                .shadow(5.dp, RoundedCornerShape(15.dp))
                .background(whiteGray, RoundedCornerShape(15.dp))
                .padding(10.dp, 15.dp, 10.dp, 5.dp)
                .align(Alignment.TopCenter)
        )
        BubbleAnimation(
            bubbleColor1 = whiteGrayOrange,
            bubbleColor2 = orange,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.BottomCenter)

        )
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "No account yet",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Register",
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        onNavigationToRegisterScreen()
                    },
                color = orange ,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

}

@Composable
fun LoginScreenContainer(
    emailValue: () -> String,
    passwordValue: () -> String,
    buttonEnabled: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonClick: () -> Unit,
    isPasswordShown: () -> Boolean,
    onTrailingPasswordIconClick: () -> Unit,
    errorHint: () -> String?,
    isLoading: () -> Boolean,
    modifier: Modifier = Modifier

) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TextEntryModule(
            modifier =Modifier.padding(10.dp),
            description = "Email address",
            hint = "papiscamara2@gmail.com",
            leadingICon = Icons.Default.Email,
            textValue = emailValue(),
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onEmailChanged,
            trailingIcon = null,
            onTrailingIconClick = null,


        )
        TextEntryModule(
            modifier =Modifier.padding(10.dp),
            description = "Password",
            hint = "Enter password",
            leadingICon = Icons.Default.VpnKey,
            textValue = passwordValue(),
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onPasswordChanged,
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = {
                onTrailingPasswordIconClick()
            },
            visualTransformation = if (isPasswordShown()) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password


        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            AuthButton(
                text = "Login",
                backgroundColor = orange,
                contentColor = white,
                onButtonClick = onLoginButtonClick,
                isLoading = isLoading(),
                enabled = buttonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(25.dp))
            )
            Text(
                text = errorHint() ?: "",
                style = MaterialTheme.typography.labelSmall
            )


        }

    }

}