package com.example.idk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.idk.ui.theme.IDKTheme

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.res.painterResource



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IDKTheme {
                ScaffoldWithGameControls()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithGameControls() {
    var showDialog by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableStateOf(0f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Slider Moon :v ") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer

                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { /* Navigate to Home */ },
                    label = { Text("Home") },
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* Navigate to Settings */ },
                    label = { Text("Settings") },
                    icon = { Icon(Icons.Filled.Settings, contentDescription = null) }
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // La columna de los tips :v
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(10) { index ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            shape = MaterialTheme.shapes.medium.copy(CornerSize(16.dp))
                        ) {
                            Text(
                                text = "Tip #${index + 1}: Play to win!",
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // La fila de las imagenes :v (son de warcraft)
                LazyRow {
                    items(5) { index ->
                        val imageResource = when (index) {
                            0 -> R.drawable.p065
                            1 -> R.drawable.p066
                            2 -> R.drawable.p074
                            3 -> R.drawable.p088
                            4 -> R.drawable.p116
                            else -> R.drawable.p117
                        }
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    painter = painterResource(id = imageResource),
                                    contentDescription = "Character Image",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // El slider para la dificultad :v
                Text(text = "Adjust Difficulty")
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    valueRange = 0f..100f,
                    steps = 5
                )

                Spacer(modifier = Modifier.height(16.dp))

                // FloatingActionButton to show settings
                FloatingActionButton(
                    onClick = { showDialog = true },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(Icons.Filled.Settings, contentDescription = "Settings")
                }

                // Mostrar el diálogo de configuración
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Settings") },
                        text = { Text("Configure game settings here.") },
                        confirmButton = {
                            Button(onClick = { showDialog = false }) {
                                Text("Ok")
                            }
                        }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IDKTheme {
        ScaffoldWithGameControls()
    }
}

