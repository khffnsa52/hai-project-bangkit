package com.capstone.hadirai.ui.screen.employee.history

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.capstone.hadirai.model.History
import com.capstone.hadirai.model.historyDummy
import com.capstone.hadirai.ui.theme.HadirAITheme

@Composable
fun HistoryScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .background(
            brush = Brush.verticalGradient(
                listOf(
                    Color(0xFF1860A5), Color(0xFFFFFFFF)
                )
            )
        )
    ) {
        Column {
            Text(
                text = "History",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFFFF),
                modifier = modifier.padding(start = 18.dp, top = 8.dp, bottom = 8.dp)
            )
            HistoryColumn(historyDummy)
        }
    }
}

@Composable
fun HistoryItem(history: History, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .border(
                width = 1.dp, color = Color(0xFF3D5A80), shape = RoundedCornerShape(size = 5.dp)
            )
            .fillMaxWidth(), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
//                .background(color = Color(0xFFFFFFFF))
        ) {

            Text(
                text = history.date, fontSize = 18.sp, fontWeight = FontWeight.Normal
            )
            Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
                AsyncImage(
                    model = history.imgAwal,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = history.waktuMasuk!!, fontSize = 14.sp, fontWeight = FontWeight.Light
                )


                AsyncImage(
                    model = history.imgAkhir,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = history.waktuKeluar!!, fontSize = 14.sp, fontWeight = FontWeight.Light
                )

            }
        }
    }
}

@Composable
fun HistoryColumn(listHistory: List<History>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 8.dp),
        modifier = modifier
    ) {
        items(listHistory, key = { it.id }) { history ->
            HistoryItem(history)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HadirAITheme {
        HistoryScreen()
    }
}