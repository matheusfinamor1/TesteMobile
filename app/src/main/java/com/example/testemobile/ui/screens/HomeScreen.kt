package com.example.testemobile.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testemobile.ui.theme.TesteMobileTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    TesteMobileTheme {
        HomeScreen()
    }
}