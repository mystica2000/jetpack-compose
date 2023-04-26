package com.example.mytest

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(name: String) {

    val isBlinking = remember { mutableStateOf(true) }
    val context = LocalContext.current

    val backgroundColor by animateColorAsState(
        targetValue = if (isBlinking.value) Color(Random.nextInt(256),Random.nextInt(256), Random.nextInt(256)) else Color.Transparent,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700),
            repeatMode = RepeatMode.Reverse
        )
    )

    Scaffold(
        topBar = {
                 CenterAlignedTopAppBar(title = {
                    Text(
                        text = "$name",
                        color = Color.White
                    )
                 }, navigationIcon = {
                   IconButton(onClick = {
                       Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT ).show()
                   }) {
                       Icon(imageVector = Icons.Filled.Menu, contentDescription = "Localized description")
                   }
                 },
                     )
        },
        content = { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                color = backgroundColor
            ) {
                Text(
                    text = "Hello $name!", modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    )


    LaunchedEffect(name) {
        isBlinking.value = !isBlinking.value
    }
}
