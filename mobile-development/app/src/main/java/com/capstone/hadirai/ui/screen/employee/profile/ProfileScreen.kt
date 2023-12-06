package com.capstone.hadirai.ui.screen.employee.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.capstone.hadirai.model.User
import com.capstone.hadirai.model.dummyMenu
import com.capstone.hadirai.model.dummyPengumuman
import com.capstone.hadirai.model.userMenu
import com.capstone.hadirai.ui.components.DetailInformation
import com.capstone.hadirai.ui.components.MenuRow
import com.capstone.hadirai.ui.components.PengumumanColumn
import com.capstone.hadirai.ui.screen.employee.history.HistoryScreen
import com.capstone.hadirai.ui.theme.HadirAITheme

@Composable
fun ProfileScreen (modifier: Modifier = Modifier) {
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
                text = "Profile",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFFFF),
                modifier = modifier.padding(start = 18.dp, top = 8.dp, bottom = 8.dp)
            )

        }

    }
}

@Composable
fun Profile (user: User= userMenu, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier){
        AsyncImage(
            model = user.imgUser,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            text = user.nama,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    HadirAITheme {
        Profile()
    }
}