package com.chatwith.app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryScreen(onBack: () -> Unit, onResumeChat: (String) -> Unit) {
    val historyList = listOf(
        "Chat with Einstein on Relativity",
        "Shakespeare: Meaning of Life",
        "Cleopatra: Ancient Egypt Politics"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Chat History",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(historyList.size) { index ->
                val chat = historyList[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { onResumeChat(chat) }
                ) {
                    Text(
                        text = chat,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onBack() }) {
            Text("Back")
        }
    }
}
