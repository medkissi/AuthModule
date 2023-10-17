package com.medkissi.authmodule.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.medkissi.authmodule.utils.ScreenRoutes

@Composable
fun FinalDestinationScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text ="Welcome",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }

}