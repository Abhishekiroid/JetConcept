package com.example.jetcompose.screens


import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay


@Composable
@Preview(showSystemUi = true)
fun state() {
    count()
}

@Composable
fun count (){
    // here we are using remember state to get the count value save
    val count= remember {
        mutableStateOf(0)
    }
    // here launch effect is used to call only at intial recomposition
    // here iam  provide to 2 sec delay and then after count value is changed to 10
    // this simple scenario
    LaunchedEffect(key1 = Unit){
        delay(2000)
        count.value= 10
    }
//    Text(text = count.value.toString())
    // now here if call this function
   countChange(count.value)
 }

@Composable
fun countChange(value: Int) {
    // here iam calling again launch effect
    // now delay is 5 sec the expected value is 10
    // but it will print in log 0 but on screen it is 10 because launch effect will call only
    // at intial recomposition and when key is change or updated so we want that always get updated value
    // we use remenberUpdate state
    val state= rememberUpdatedState(newValue = value)
    LaunchedEffect(key1 = Unit ){
        delay(5000)
        Log.d("ChangeCount",state.value.toString())
    }
    Text(text = state.value.toString())

}