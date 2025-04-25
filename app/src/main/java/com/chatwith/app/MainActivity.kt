package com.chatwith.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.chatwith.app.ui.theme.ChatWithTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatWithTheme {
                ChatWithApp()
            }
        }
    }
}

@Composable
fun ChatWithApp() {
    var isLoggedIn by remember { mutableStateOf(false) }
    var selectedAI by remember { mutableStateOf<String?>(null) }
    var showHistory by remember { mutableStateOf(false) }
    var showProfile by remember { mutableStateOf(false) }

    when {
        !isLoggedIn -> {
            LoginScreen(onLoginSuccess = { isLoggedIn = true })
        }

        showProfile -> {
            ProfileScreen(
                onBack = { showProfile = false },
                onLogout = {
                    isLoggedIn = false
                    selectedAI = null
                    showHistory = false
                    showProfile = false
                }
            )
        }

        showHistory -> {
            HistoryScreen(
                onBack = { showHistory = false },
                onResumeChat = { chatTitle ->
                    selectedAI = chatTitle
                    showHistory = false
                }
            )
        }

        selectedAI == null -> {
            HomeScreen(
                onChatStart = { selectedAI = it },
                onHistoryClick = { showHistory = true },
                onProfileClick = { showProfile = true }
            )
        }

        else -> {
            ChatScreen(
                selectedCharacter = selectedAI!!,
                onBack = { selectedAI = null }
            )
        }
    }
}
