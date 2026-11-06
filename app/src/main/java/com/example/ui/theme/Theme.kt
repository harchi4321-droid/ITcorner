package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val VpnDarkColorScheme = darkColorScheme(
    primary = NeonCyan,
    onPrimary = Color(0xFF00363D),
    primaryContainer = Color(0xFF004F58),
    onPrimaryContainer = NeonCyanLight,
    secondary = ShweGold,
    onSecondary = Color(0xFF422C00),
    secondaryContainer = Color(0xFF5F4100),
    onSecondaryContainer = ShweGold,
    tertiary = CyberGreen,
    onTertiary = Color(0xFF003918),
    tertiaryContainer = Color(0xFF005326),
    onTertiaryContainer = CyberGreen,
    background = MidnightBg,
    onBackground = TextPrimary,
    surface = SurfaceDark,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceCard,
    onSurfaceVariant = TextSecondary,
    outline = SurfaceCardBorder,
    error = CyberRed,
    onError = Color.White
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // VPN apps look best with futuristic cyber dark mode
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = VpnDarkColorScheme,
        typography = Typography,
        content = content
    )
}
