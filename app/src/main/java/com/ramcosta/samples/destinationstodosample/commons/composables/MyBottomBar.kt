package com.ramcosta.samples.destinationstodosample.commons.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.samples.destinationstodosample.ui.screens.destinations.Destination
import com.ramcosta.samples.destinationstodosample.commons.title

@Composable
fun BottomBar(destination: Destination) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MaterialTheme.colors.primary)
    ) {
        Text(
            text = destination.title?.let { stringResource(it) } ?: "",
            modifier = Modifier.align(Alignment.Center),
            color = Color.White
        )
    }
}