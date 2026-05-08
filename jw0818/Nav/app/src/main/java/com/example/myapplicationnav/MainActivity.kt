package com.example.myapplicationnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationnav.ui.theme.MyApplicationNavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp(){
    val navController= rememberNavController()
    NavHost(navController=navController, startDestination = "home"){
        composable(route="home"){
            HomeScreen(navController=navController)
        }
        composable(route="detail"){
            DetailScreen(navController=navController)
        }
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationNavTheme {
        //Greeting("Android")
    }
}