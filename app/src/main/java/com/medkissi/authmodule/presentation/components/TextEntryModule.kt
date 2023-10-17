package com.medkissi.authmodule.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medkissi.authmodule.presentation.ui.theme.orange
import com.medkissi.authmodule.presentation.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextEntryModule(
    modifier: Modifier = Modifier,
    description: String,
    hint: String,
    leadingICon: ImageVector,
    textValue: String,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textColor: Color,
    cursorColor: Color,
    onValueChanged: (String) -> Unit,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)?,


    ) {

    Column(modifier = modifier) {
        Text(
            text = description,
            color = textColor,
            style = MaterialTheme.typography.titleSmall
        )


        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
                .border(0.5.dp, textColor, RoundedCornerShape(25.dp))
                .height(50.dp)
                .shadow(3.dp, RoundedCornerShape(25.dp)),

            value = textValue,
            onValueChange = onValueChanged,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = white,
                cursorColor = cursorColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent

            ),
            shape = RoundedCornerShape(25.dp),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = leadingICon,
                    contentDescription = null,
                    tint = cursorColor

                )
            },
            trailingIcon = {
                if (trailingIcon != null) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            if (onTrailingIconClick != null) onTrailingIconClick()

                        },
                        tint = cursorColor
                    )
                }
            },
            placeholder = {
                Text(
                    text = hint,
                    textAlign = TextAlign.Start
                )
            },
            textStyle = MaterialTheme.typography.titleSmall,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation


        )


    }


}

@Preview(showBackground = true)
@Composable
fun TextEntryModulePreview() {
    TextEntryModule(
        modifier= Modifier.padding(10.dp),
        description = "Email address",
        hint = "papiscamara2@gmail.com",
        leadingICon = Icons.Default.Email,
        textValue = "papiscamara2@gmail.com",
        textColor = Color.Black,
        cursorColor = orange,
        onValueChanged = {},
        onTrailingIconClick = { /*TODO*/ },
        )


}

