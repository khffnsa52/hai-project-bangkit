package com.capstone.hadirai.ui.screen.employee.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.hadirai.model.dummyMenu
import com.capstone.hadirai.model.dummyPengumuman
import com.capstone.hadirai.ui.components.DetailInformation
import com.capstone.hadirai.ui.components.MenuRow
import com.capstone.hadirai.ui.components.PengumumanColumn
import com.capstone.hadirai.ui.theme.HadirAITheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               navigationToMaps : (Int) -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ){
        DetailInformation()
        MenuRow(listMenu = dummyMenu, navigationToMaps = navigationToMaps)
        Text(
            text = "Pengumuman",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(start = 18.dp, top = 8.dp, bottom = 8.dp)
        )
        PengumumanColumn(listPengumuman = dummyPengumuman)
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HadirAITheme {
        HomeScreen(navigationToMaps = {})
    }
}