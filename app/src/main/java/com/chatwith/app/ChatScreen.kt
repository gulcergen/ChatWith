package com.chatwith.app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatScreen(selectedCharacter: String, onBack: () -> Unit) {
    var userMessage by remember { mutableStateOf("") }
    var chatHistory by remember { mutableStateOf(listOf<String>()) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // Başlık
        Text(
            text = "Chatting with $selectedCharacter",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Mesaj geçmişi
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            chatHistory.forEach { message ->
                Text(
                    text = message,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }

        // Mesaj yazma alanı
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = userMessage,
                onValueChange = { userMessage = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = { Text("Say something...") }
            )

            Button(onClick = {
                if (userMessage.isNotBlank()) {
                    chatHistory = chatHistory + "You: $userMessage" +
                            "\n$selectedCharacter: This is a mock reply 🤖"
                    userMessage = ""
                }
            }) {
                Text("Send")
            }
        }
    }
}
