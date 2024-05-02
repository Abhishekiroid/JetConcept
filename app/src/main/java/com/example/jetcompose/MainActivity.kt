package com.example.jetcompose


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetcompose.screens.QuoteDetail
import com.example.jetcompose.screens.QuoteListScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch{
            DataManager.getAssetFromFile(applicationContext)
        }
        setContent {
            App()
        }
    }
}
@Composable
fun App(){
  if (DataManager.isDataLoaded.value){
      if (DataManager.currentPage.value==Pages.LISTING){
          QuoteListScreen(data = DataManager.data) {
          DataManager.switchPages(it)
          }
      }else{
          DataManager.currentQuote?.let { QuoteDetail(quote = it) }
      }

  }else{
      Box (contentAlignment = Alignment.Center,
          modifier = Modifier.fillMaxSize(1f))
      {
        Text(text = "Loading...",
            style = MaterialTheme.typography.bodyMedium)
      }
  }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Login Page",
            style = TextStyle(
                color = Color.Black, fontSize = 50.sp,
                textDecoration = TextDecoration.Underline,
            ), textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
                .wrapContentSize()
                .wrapContentHeight()
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .absolutePadding(0.dp, 120.dp, 0.dp, 150.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        var username by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("") }
        val shape = CircleShape
        val mContext= LocalContext.current
        TextField(
            value = username,
            leadingIcon = { Icon(imageVector= Icons.Default.Email,contentDescription = "emailIcon") },
            onValueChange = { username = it },
            label = { Text("Enter username") },
            placeholder = { Text(text = "Enter your username") },
            modifier = Modifier.absolutePadding(0.dp,20.dp,0.dp,0.dp)
        )
        TextField(
            value = pass,
            leadingIcon = { Icon(imageVector= Icons.Default.Lock,contentDescription = "passIcon") },
            onValueChange = { pass = it },
            label = { Text("Enter Password") },
            placeholder = { Text(text = "Enter your password") },
            modifier = Modifier.absolutePadding(0.dp,20.dp,0.dp,0.dp)
        )
        Button(onClick = {
         shift(mContext,username,pass)
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .absolutePadding(0.dp, 20.dp, 0.dp, 0.dp)
                .border(2.dp, color = Color.Red, shape = shape)

        )
        {
            Text(
                text = "Login",
                style = TextStyle(
                    fontSize = 20.sp
                ),
                modifier = Modifier
                .padding(5.dp)

            )
        }
    }

}
enum class Pages{
    LISTING,DETAIL
}

fun shift(context: Context, userName:String,passWord:String) {
     if (userName.trim().contentEquals("Abhi123") && passWord.trim().contentEquals("Password")){
         context.startActivity(Intent(context, SecondActivity::class.java))
     }else if(userName.isEmpty()){
         Toast.makeText(context,"Please enter the username",Toast.LENGTH_LONG).show()
     }else if (passWord.isEmpty()){
         Toast.makeText(context,"Please enter the password",Toast.LENGTH_LONG).show()
     }else{
         Toast.makeText(context,"Something Went Wrong",Toast.LENGTH_LONG).show()
     }

}