package com.example.jetcompose.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Composable
@Preview(showSystemUi = true)
fun ProduceState() {
    // In produce state it used to state that it will produce two work
    // 1. state is being produce  2. it will update value but that is define by dev
    // it is combination of state and launchEffect and inside {
    // here we can perform async task}
    val state= produceState(initialValue = 0){
        for (i in 1 ..10){
            delay(1000)
            value+=1
        }
    }
    Text(text = state.value.toString(),
        style = MaterialTheme.typography.bodyMedium)
}