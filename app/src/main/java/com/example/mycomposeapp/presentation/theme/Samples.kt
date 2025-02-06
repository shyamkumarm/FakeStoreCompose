package com.example.mycomposeapp.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun WrapWidthExample() {
    Row() {
        Surface(
            modifier = Modifier
                .size(200.dp)
                .border(2.dp, Color.Yellow),
            onClick = {}) {
            Column(
                modifier = Modifier
                    .background(Color.Red, RoundedCornerShape(6.dp))
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(Color.Green, RoundedCornerShape(6.dp))
                )

            }
        }
        Surface(
            modifier = Modifier
                .size(200.dp)
                .border(2.dp, Color.Yellow),
            onClick = {}) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .background(Color.Red, RoundedCornerShape(6.dp))
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Green, RoundedCornerShape(6.dp))
                )

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Black, RoundedCornerShape(6.dp))
                )

            }
        }
    }
}