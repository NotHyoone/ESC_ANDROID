package com.example.nav2

import android.R.attr.button
import android.R.attr.onClick
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nav2.ui.theme.Nav2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Wrapped MyApp in your theme for consistent styling
            Nav2Theme {
                MyApp()
            }
        }
    }
} // Removed the extra closing braces that were here

@Composable
fun MyApp() {
    val navController = rememberNavController()

    // Fixed typo: 'navContoller' changed to 'navController'
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(navController = navController)
        }

        composable (route = "detail"){
            DetailScreen(navController =navController)

        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Next Screen",  fontSize = 24.sp )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {navController.navigate("detail")}) {
                Text("Next Screen")
            }
        }
    }


@Composable
fun DetailScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text("여기는 상세 화면입니다.", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.popBackStack() }) { Text("뒤로가기") }
    }

}

