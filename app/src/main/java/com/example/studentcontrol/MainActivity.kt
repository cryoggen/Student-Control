package com.example.studentcontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studentcontrol.ui.theme.StudentControlTheme


val testListPractices =
    listOf("Русский язык", "Алгебра", "Природоведенье", "Литература", "Английский язык")
val testListTasks =
    listOf("Контрольная", "Домашняя Работа", "Зачёт", "Экзамент", "Английский язык")

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentControlTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StartApp()
                }
            }
        }
    }
}

@Composable
fun StartApp() {
    val navController = rememberNavController()
    NavHost(navController)
}

@Composable
fun NavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "list_practices",
        modifier = modifier
    ) {
        composable("list_practices") {
            ListItems(
                practices = testListPractices,
                onClick = { navController.navigate("list_tasks") })
        }
        composable("list_tasks") {
            ListItems(
                practices = testListTasks,
                onClick = {
                    TODO()
                })
        }
    }
}


@Composable
private fun ListItems(practices: List<String> = testListPractices, onClick: () -> Unit) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = practices) { practice ->
            Practice(practice = practice, onClick = onClick)
        }
    }
}


@Composable
private fun Practice(practice: String, onClick: () -> Unit = {}) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp).clickable(onClick = onClick)
    ) {
        PracticeText(practice)
    }
}

@Composable
fun PracticeText(practice: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)

    ) {
        Text(
            text = practice, style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    StudentControlTheme {
        StartApp()
    }
}