package com.example.jetcompose

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetcompose.data.EmployeeData

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                AddData()
        }
    }

    @Composable
    @Preview(showSystemUi = true)
    fun AddData() {
        var isView = remember {
            mutableStateOf(false)
        }
        val mContext = LocalContext.current
        NaviSide()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(0.dp, 50.dp, 0.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val shape = CircleShape
            Button(
                onClick = {
                    isView.value = !isView.value
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .absolutePadding(0.dp, 20.dp, 0.dp, 0.dp)
                    .border(2.dp, color = Color.Red, shape = shape)

            )
            {
                Text(
                    text = if (isView.value) "Hide" else "Show",
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .padding(5.dp)

                )
            }
            if (isView.value) {
                AddToRecycleView(mContext)
            }
        }

    }


    @Composable
    fun rowData(employeeData: EmployeeData) {
        Row {
            Text(
                text = employeeData.id, fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                letterSpacing = 5.sp
            )
            Text(
                text = employeeData.name, fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp)
            )
            Text(
                text = employeeData.age, fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp)
            )
            Text(
                text = employeeData.designation, fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp)
            )
        }
    }

    @Composable
    fun AddToRecycleView(mContext: Context) {
        val employeeList = mutableListOf<EmployeeData>()
        employeeList.add(EmployeeData("Abhishek", "Android Developer", "1", "25", "Android development"))
        employeeList.add(EmployeeData("Deepak", "Android Developer", "2", "23", "Android development"))
        employeeList.add(EmployeeData("Dixit", "Android Developer", "3", "23", "Android development"))
        employeeList.add(EmployeeData("Meet", "Android Developer", "4", "22", "Android development"))
        employeeList.add(EmployeeData("Manish", "UI Developer", "5", "23", "UI UX development"))
        employeeList.add(EmployeeData("Laxman", "IOS Developer", "6", "28", "Apple development"))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(0.dp, 30.dp, 0.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxSize()
                    .height(40.dp)
            ) {
                items(employeeList) { model ->
                    rowData(employeeData = model)
                }
            }
        }

    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun NaviSide() {
        Scaffold(
            topBar = { topBar()},
            content = { Text("BodyContent") },
            bottomBar = {bottomBar()}
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun topBar() {
        Column {
            CenterAlignedTopAppBar(title = {
                Text("HomeActivity")
            },
                navigationIcon = {
                    IconButton(onClick = {/* Do Something*/ }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }, actions = {
                    IconButton(onClick = {/* Do Something*/ }) {
                        Icon(Icons.Filled.Share, null)
                    }
                    IconButton(onClick = {/* Do Something*/ }) {
                        Icon(Icons.Filled.Settings, null)
                    }
                })

        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun bottomBar(){
        BottomAppBar(modifier = Modifier.background(Color(0xFF0F9D58))
        ) {
            Text(text = "Bottom App Bar", color = Color.Black)
        }
    }

}

