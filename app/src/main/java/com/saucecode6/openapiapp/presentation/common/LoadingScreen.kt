package com.saucecode6.openapiapp.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.saucecode6.openapiapp.util.Dimens.MediumPadding1

@Composable
fun LoadingScreen(message: String? = null) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(MediumPadding1),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()

            Spacer(modifier = Modifier.height(MediumPadding1))

            message?.let {
                Text(text = it)
            }
        }
    }
}
