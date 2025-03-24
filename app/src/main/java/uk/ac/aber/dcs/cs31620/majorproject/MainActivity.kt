package uk.ac.aber.dcs.cs31620.majorproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import uk.ac.aber.dcs.cs31620.majorproject.ui.theme.MajorProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapScreen()
        }
    }
}

@Composable
fun MapScreen() {
    val location = LatLng(52.4068, -4.0829) // map of Ceredigion
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 10f) // zoom level
    }

    // bounds for Ceredigion
    val bounds = LatLngBounds(
        LatLng(52.2, -4.75), // southwest corner
        LatLng(52.55, -3.6) // northeast corner
    )

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            minZoomPreference = 9f, // minimum zoom
            maxZoomPreference = 16f, // maximum zoom
            latLngBoundsForCameraTarget = bounds // restricts panning
        )
    ) {
        // markers for water bodies
        Marker(
            state = rememberMarkerState(position = location),
            title = "Ceredigion",
            snippet = "Water quality monitoring"
        )
    }
}