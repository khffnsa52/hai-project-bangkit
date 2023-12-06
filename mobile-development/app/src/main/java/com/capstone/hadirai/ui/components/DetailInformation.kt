package com.capstone.hadirai.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.hadirai.model.Menu
import com.capstone.hadirai.model.Pengumuman
import com.capstone.hadirai.model.dummyMenu
import com.capstone.hadirai.model.dummyPengumuman
import com.capstone.hadirai.ui.theme.HadirAITheme

@Composable
fun DetailInformation(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush
                    .verticalGradient(
                        listOf(
                            Color(0xFF1860A5), Color(0xFFFFFFFF)
                        )
                    )
            )
    ) {
        Text(
            text = "PT. Dicoding",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFFFFFFFF),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 18.dp, start = 18.dp)

        )
        Card(
            modifier = modifier
                .padding(start = 18.dp, top = 58.dp, end = 18.dp, bottom = 8.dp)
//                .background(color = Color(0xFFD3E3FF))
            ,
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD3E3FF)
            )


        ) {
            Column(
                modifier = modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
            ) {
                Text(
                    text = "Stephanie",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                )
                Text(
                    text = "Android Developer",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    modifier = modifier
                )
                Text(
                    text = "Senin, 20 November 2023",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    modifier = modifier
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Time()
                    Time()
                }
            }
        }
    }

}


@Composable
fun Time(modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ), modifier = modifier.padding(8.dp)
    ) {
        Column(
            modifier = modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Waktu Pulang",
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = "17.45",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun MenuItem(
    menu: Menu, modifier: Modifier = Modifier, navigationToMaps: (Int) -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Column {
            Image(
                painter = painterResource(menu.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { navigationToMaps }
            )
        }
    }
}

@Composable
fun PengumumanItem(pengumuman: Pengumuman, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0xFF3D5A80),
                shape = RoundedCornerShape(size = 5.dp)
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
                text = pengumuman.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = pengumuman.detail,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = pengumuman.date,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}


@Composable
fun MenuRow(
    listMenu: List<Menu>, modifier: Modifier = Modifier,
    navigationToMaps: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 18.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.title }) { menu ->
            MenuItem(menu, navigationToMaps = navigationToMaps)
        }
    }
}


@Composable
fun PengumumanColumn(listPengumuman: List<Pengumuman>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 8.dp),
        modifier = modifier
    ) {
        items(listPengumuman, key = { it.title }) { pengumuman ->
            PengumumanItem(pengumuman)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailInformationPreview() {
    HadirAITheme {
//        PengumumanColumn(dummyPengumuman)
        DetailInformation()
    }
}