package com.medkissi.authmodule.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medkissi.authmodule.presentation.ui.theme.orange
import com.medkissi.authmodule.presentation.ui.theme.white

@Composable
fun AuthButton(
    text:String,
    backgroundColor:Color,
    contentColor:Color,
    enabled:Boolean = true,
    onButtonClick:()->Unit,
    isLoading:Boolean,
    modifier:Modifier =Modifier


) {
    Button(
        modifier = modifier,
        onClick = {
        onButtonClick()

        },
        colors = ButtonDefaults.buttonColors(
            containerColor =  backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = backgroundColor,
            disabledContentColor = contentColor
        ),
        enabled = enabled
    ) {
        if(isLoading){
            CircularProgressIndicator(
                modifier=Modifier.size(20.dp),
                color = contentColor
            )
        }else{
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun AuthButtonPreview() {
    AuthButton(
        text = "Login",
        backgroundColor = orange ,
        contentColor = white,
        onButtonClick = { /*TODO*/ },
        isLoading = true,
        modifier = Modifier.fillMaxWidth()
    )

}
