package com.example.myapplication4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication4.ui.theme.MyApplication4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp(){
    //1. 기관사 준비 : 화면 이동을 책임지는 객체
    val navController = rememberNavController()

    //2. 창문(NavHost)과 노선도(NavGraph) 설정
    //startDestination : 앱을 켰을때 가장 먼저 보여줄 화면의 이름 설정
    NavHost(navController = navController, startDestination = "home"){
        composable(route = "hame"){
            HomeScreen(navController = navController)
        }
        composable(route = "detail"){
            DetailScreen(navController = navController)
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text("여기는 홈 화면입니다.", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate("detail") }){ Text("상세 화면으로 이동")}
    }
}

@Composable
fun DetailScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("여기는 상세 화면입니다.", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            // 이전 화면으로 돌아가라! (Back 버튼과 동일한 역할)
            navController.popBackStack()
        }) {
            Text("뒤로 가기")
        }
    }
}

