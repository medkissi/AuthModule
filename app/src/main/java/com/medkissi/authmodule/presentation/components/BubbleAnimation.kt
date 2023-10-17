package com.medkissi.authmodule.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medkissi.authmodule.presentation.ui.theme.gray
import com.medkissi.authmodule.presentation.ui.theme.orange
import com.medkissi.authmodule.presentation.ui.theme.redOrange
import com.medkissi.authmodule.presentation.ui.theme.white



@Composable
fun BubbleAnimation(
    bubbleColor1:Color,
    bubbleColor2:Color,
    modifier: Modifier =Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    Box(modifier = modifier){
        val xValue by infiniteTransition.animateFloat(
            initialValue = 100f ,
            targetValue = 1340f,
            animationSpec = infiniteRepeatable(
                animation = tween(7000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        val yValue by infiniteTransition.animateFloat(
            initialValue = 100f ,
            targetValue = 700f,
            animationSpec = infiniteRepeatable(
                animation = tween(6000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        val xValue1 by infiniteTransition.animateFloat(
            initialValue = 1340f ,
            targetValue =100f ,
            animationSpec = infiniteRepeatable(
                animation = tween(8000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        val yValue1 by infiniteTransition.animateFloat(
            initialValue = 400f ,
            targetValue = 200f,
            animationSpec = infiniteRepeatable(
                animation = tween(7000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        val xValue2 by infiniteTransition.animateFloat(
            initialValue = 1000f ,
            targetValue = 400f,
            animationSpec = infiniteRepeatable(
                animation = tween(7500, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        val yValue2 by infiniteTransition.animateFloat(
            initialValue = 150f ,
            targetValue = 600f,
            animationSpec = infiniteRepeatable(
                animation = tween(6000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ){
            drawCircle(
                brush = Brush.linearGradient(
                    listOf(bubbleColor1,bubbleColor2),
                    start = Offset(xValue-90,yValue),
                    end =  Offset(xValue+90,yValue)
                ),
                radius = 100f,
                center = Offset(xValue,yValue)

            )
            drawCircle(
                brush = Brush.linearGradient(
                    listOf(bubbleColor1,bubbleColor2),
                    start = Offset(xValue1-90,yValue1),
                    end =  Offset(xValue1+90,yValue1)
                ),
                radius = 100f,
                center = Offset(xValue1,yValue1)

            )
            drawCircle(
                brush = Brush.linearGradient(
                    listOf(bubbleColor1,bubbleColor2),
                    start = Offset(xValue2-90,yValue2),
                    end =  Offset(xValue2+90,yValue2)
                ),
                radius = 80f,
                center = Offset(xValue2,yValue2)

            )
        }
        
    }



}

@Preview(showBackground = true )
@Composable
fun BubbleAnimationPreview() {
    BubbleAnimation(
        bubbleColor1 = orange,
        bubbleColor2 = gray )
    
}
