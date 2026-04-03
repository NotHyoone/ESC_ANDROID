package com.example.myapplication

import android.os.Bundle    // 액티비티를 만들기 위해 필요
// 앱의 기본 구성요소를 가져옵니다
import androidx.activity.ComponentActivity // 액티비티를 만들기 위해 필요
import androidx.activity.compose.setContent // 액티비티의 레이아웃을 설정하는데 사용
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image // 이미지 표시용
import androidx.compose.ui.res.painterResource // 리소스 불러오기용
import androidx.compose.foundation.layout.Box // 겹치기 위한 컨테이너
import androidx.compose.ui.layout.ContentScale // 이미지 채우기 방식 설정
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.animation.*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 앱의 테마를 설정합니다 (기본 설정)
            MaterialTheme {
                // 배경색을 채운 화면 컨테이너
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScreen() // 우리가 만든 화면 함수 호출
                }
            }
        }

    }
}

@Composable
fun MyNavApp() {
    // 1. 화면 이동을 담당하는 조종사(Controller)를 만듭니다.
    val navController = rememberNavController()

    // 2. 화면 지도(NavHost)를 그려줍니다.
    NavHost(
        navController = navController,
        startDestination = "login_page" // 앱을 켰을 때 처음 보여줄 주소
    ) {
        composable(
            route = "main_pager",
            enterTransition = { // 화면이 들어올 때 효과
                slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn()
            },
            exitTransition = { // 화면이 나갈 때 효과
                slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut()
            }
        ) {
            ImagePagerScreen()
        }

        // [이미지 페이저 화면의 주소 설정]
        composable("main_pager") {
            ImagePagerScreen()
        }
    }
}

@Composable
fun MyScreen() {
    // 1. 현재 어떤 화면을 보여줄지 결정하는 상태 (login 또는 main)
    var currentScreen by remember { mutableStateOf("login") }

    // 2. 화면 상태에 따라 다른 Composable 호출
    if (currentScreen == "login") {
        LoginScreen(onLoginSuccess = { currentScreen = "main" })
    } else {
        ImagePagerScreen()
    }
}
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("로그인", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = id, onValueChange = { id = it }, label = { Text("아이디") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = pw, onValueChange = { pw = it }, label = { Text("비밀번호") },
            visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                // 아이디와 비밀번호가 비어있지 않으면 성공으로 간주
                if (id.isNotEmpty() && pw.isNotEmpty()) {
                    onLoginSuccess()
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("로그인하기")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePagerScreen() {
    val images = listOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img5,
        R.drawable.img6,
        R.drawable.img7,
        R.drawable.img8,
        R.drawable.img9,
        R.drawable.img10
    ) // 파일명 확인!
    val pagerState = rememberPagerState(pageCount = { images.size })

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "드래그해서 넘겨보세요",
            color = Color.White,
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 50.dp)
        )
    }
}