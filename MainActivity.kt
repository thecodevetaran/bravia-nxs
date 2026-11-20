package com.bravia.nexus

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.*
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BraviaNexusTheme {
                MainDashboardScreen(
                    onLaunchApp = { packageName ->
                        launchApp(packageName)
                    },
                    onOpenSettings = { intentAction ->
                        openSystemSettings(intentAction)
                    }
                )
            }
        }
    }

    private fun launchApp(packageName: String) {
        try {
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                startActivity(intent)
            } else {
                val marketIntent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse("market://details?id=$packageName"))
                startActivity(marketIntent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun openSystemSettings(action: String) {
        try {
            startActivity(Intent(action))
        } catch (e: Exception) {
            try {
                startActivity(Intent(Settings.ACTION_SETTINGS))
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}

@Composable
fun BraviaNexusTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF56D9FF),
            secondary = Color(0xFF24A9D6),
            background = Color(0xFF080D15),
            surface = Color(0xFF172433),
            onBackground = Color(0xFFE8F2FA),
            onSurface = Color(0xFFE8F2FA)
        ),
        content = content
    )
}

@Composable
fun MainDashboardScreen(
    onLaunchApp: (String) -> Unit,
    onOpenSettings: (String) -> Unit
) {
    var currentTime by remember { mutableStateOf("") }
    var currentDate by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (true) {
            val now = Date()
            currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(now)
            currentDate = SimpleDateFormat("EEEE, MMMM d", Locale.getDefault()).format(now)
            delay(1000)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF080D15), Color(0xFF101B28))
                )
            )
            .padding(48.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "BRAVIA NEXUS",
                        color = Color(0xFF56D9FF),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                    Text(
                        text = "Sony UR3 • Android TV 10",
                        color = Color(0xFF8FA6B8),
                        fontSize = 12.sp
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = if (currentTime.isEmpty()) "--:--" else currentTime,
                        color = Color(0xFFE8F2FA),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Light
                    )
                    Text(
                        text = if (currentDate.isEmpty()) "Loading..." else currentDate,
                        color = Color(0xFF8FA6B8),
                        fontSize = 14.sp
                    )
                }
            }

            // Featured Cinematic Hero Panel
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF172433))
                    .padding(32.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(4.dp)),
                        colors = ClickableSurfaceDefaults.colors(
                            containerColor = Color(0xFF56D9FF).copy(alpha = 0.2f),
                            focusedContainerColor = Color(0xFF56D9FF)
                        ),
                        onClick = {}
                    ) {
                        Text(
                            text = "FEATURED CINEMATIC",
                            color = Color(0xFF56D9FF),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                    Text(
                        text = "Blade Runner 2049",
                        color = Color(0xFFE8F2FA),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Experience stunning 4K HDR clarity optimized for your Sony KD-55A8H OLED display.",
                        color = Color(0xFF8FA6B8),
                        fontSize = 14.sp
                    )
                }
            }

            // Application Dock
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "STREAMING & APPLICATIONS",
                    color = Color(0xFF8FA6B8),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    AppCard("Netflix", "com.netflix.ninja", onLaunchApp)
                    AppCard("YouTube", "com.google.android.youtube.tv", onLaunchApp)
                    AppCard("Prime Video", "com.amazon.avod.thirdpartyclient", onLaunchApp)
                    AppCard("Disney+", "com.disney.disneyplus", onLaunchApp)
                    AppCard("Spotify", "com.spotify.tv.android", onLaunchApp)
                }
            }

            // Quick Settings & Footer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    SettingButton("Wi-Fi", Settings.ACTION_WIFI_SETTINGS, onOpenSettings)
                    SettingButton("Bluetooth", Settings.ACTION_BLUETOOTH_SETTINGS, onOpenSettings)
                    SettingButton("Display", Settings.ACTION_DISPLAY_SETTINGS, onOpenSettings)
                    SettingButton("Sound", Settings.ACTION_SOUND_SETTINGS, onOpenSettings)
                    SettingButton("All Settings", Settings.ACTION_SETTINGS, onOpenSettings)
                }

                Text(
                    text = "BRAVIA NEXUS v1.0 • SECURE HUD",
                    color = Color(0xFF8FA6B8),
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun AppCard(title: String, packageName: String, onClick: (String) -> Unit) {
    Surface(
        onClick = { onClick(packageName) },
        shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(12.dp)),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = Color(0xFF172433),
            focusedContainerColor = Color(0xFF24A9D6)
        ),
        modifier = Modifier
            .width(180.dp)
            .height(90.dp)
            .focusable()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = Color(0xFFE8F2FA),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun SettingButton(title: String, action: String, onClick: (String) -> Unit) {
    Surface(
        onClick = { onClick(action) },
        shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(8.dp)),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = Color(0xFF101B28),
            focusedContainerColor = Color(0xFF56D9FF)
        ),
        modifier = Modifier.height(40.dp)
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = Color(0xFFE8F2FA),
                fontSize = 13.sp
            )
        }
    }
}
