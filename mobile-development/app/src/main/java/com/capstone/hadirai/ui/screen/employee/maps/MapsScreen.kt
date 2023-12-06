package com.capstone.hadirai.ui.screen.employee.maps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.hadirai.ui.screen.employee.home.HomeScreen
import com.capstone.hadirai.ui.theme.HadirAITheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapsScreen(modifier : Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)) {
        Maps(modifier = modifier.padding(start = 18.dp, top = 8.dp, bottom = 8.dp))
        Text(
            text = "Sabar, blom ada. Semangat Guyss!!!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(start = 18.dp, top = 8.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun Maps(modifier: Modifier = Modifier) {
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = modifier.padding(16.dp)
            .fillMaxWidth(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = singapore),
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MapsScreenPreview() {
    HadirAITheme {
        MapsScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun MapsPreview() {
    HadirAITheme {
        Maps()
    }
}