package com.example.testemobile

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.testemobile.ui.screens.HomeScreen
import com.example.testemobile.ui.theme.TesteMobileTheme
import com.example.testemobile.ui.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TesteMobileTheme {
                val viewModel: HomeScreenViewModel = koinViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                ) { paddingValues ->
                    HomeScreen(
                        uiState = uiState,
                        viewModel = viewModel,
                    )
                    paddingValues
                }
            }
        }
    }
}

