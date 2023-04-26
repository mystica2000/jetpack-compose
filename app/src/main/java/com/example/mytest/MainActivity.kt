package com.example.mytest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytest.ui.theme.MytestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MytestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val tabs:List<NavigationItem> = listOf<NavigationItem>(
                        NavigationItem ( Icons.Filled.Home,"Home"),
                        NavigationItem ( Icons.Filled.Search,"Search"),
                        NavigationItem ( Icons.Filled.Person,"Profile"),
                    )
                    val selectedTabIndex = remember { mutableStateOf(0) }
                    Navigation(selectedTabIndex,tabs)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(selectedTabIndex: MutableState<Int>, tabs: List<NavigationItem>,
modifier: Modifier = Modifier, navController:NavHostController = rememberNavController(),
startDestination: String = tabs.get(selectedTabIndex.value).tabName) {
    print(startDestination)
    Scaffold(
        bottomBar = {
            BottomAppBar {
                NavigationBar {
                    tabs.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(item.iconName, contentDescription = item.tabName) },
                            label = { Text(item.tabName) },
                            selected = selectedTabIndex.value == index,
                            onClick = { selectedTabIndex.value = index }
                        )
                    }
                }
            }
        },
        content = {
                padding ->
            Column(
                modifier = Modifier
                    .padding(padding) ) {

                  NavHost(navController = navController, modifier = modifier, startDestination = startDestination) {
                      composable("Search") {
                          Home("Search")
                      }
                      composable("Profile") {
                          Home("Profile")
                      }
                      composable("Home") {
                          Home("Home")
                      }
                  }

            }
        },
    )
   

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MytestTheme {
    }
}