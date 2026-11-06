package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.ripple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppLanguage
import com.example.model.ConnectionStatus
import com.example.ui.theme.CyberGreen
import com.example.ui.theme.CyberRed
import com.example.ui.theme.GlowCyan
import com.example.ui.theme.GlowGreen
import com.example.ui.theme.MidnightBg
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.ShweGold
import com.example.ui.theme.SurfaceCard
import com.example.ui.util.Localization

@Composable
fun ConnectButton(
    status: ConnectionStatus,
    durationText: String,
    language: AppLanguage,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse_rings")

    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.25f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulse_scale"
    )

    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulse_alpha"
    )

    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val isConnected = status == ConnectionStatus.CONNECTED
    val isConnecting = status == ConnectionStatus.CONNECTING

    val primaryAccent = when {
        isConnected -> CyberGreen
        isConnecting -> ShweGold
        status == ConnectionStatus.ERROR -> CyberRed
        else -> NeonCyan
    }

    val glowColor = when {
        isConnected -> GlowGreen
        isConnecting -> ShweGold.copy(alpha = 0.25f)
        else -> GlowCyan
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(240.dp)
        ) {
            // Pulsing Radar rings when connecting or connected
            if (isConnected || isConnecting) {
                Canvas(
                    modifier = Modifier
                        .size(230.dp)
                        .scale(pulseScale)
                ) {
                    drawCircle(
                        color = primaryAccent.copy(alpha = pulseAlpha),
                        style = Stroke(width = 4.dp.toPx())
                    )
                }
            }

            // Outer Orbit Arc
            Canvas(
                modifier = Modifier
                    .size(210.dp)
                    .rotate(if (isConnected || isConnecting) rotationAngle else 0f)
            ) {
                val strokeWidth = 3.dp.toPx()
                drawArc(
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            primaryAccent.copy(alpha = 0.1f),
                            primaryAccent.copy(alpha = 0.4f),
                            primaryAccent,
                            primaryAccent.copy(alpha = 0.1f)
                        )
                    ),
                    startAngle = 0f,
                    sweepAngle = 270f,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }

            // Main Central Tappable Disc
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(170.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                SurfaceCard,
                                MidnightBg
                            )
                        )
                    )
                    .shadow(
                        elevation = if (isConnected) 24.dp else 12.dp,
                        shape = CircleShape,
                        spotColor = primaryAccent
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(bounded = true, color = primaryAccent),
                        onClick = onToggle
                    )
                    .testTag("connect_vpn_button")
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = when {
                            isConnected -> Icons.Default.Shield
                            isConnecting -> Icons.Default.Lock
                            else -> Icons.Default.PowerSettingsNew
                        },
                        contentDescription = "VPN Connection Toggle",
                        tint = primaryAccent,
                        modifier = Modifier.size(48.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    if (isConnected && durationText.isNotEmpty()) {
                        Text(
                            text = durationText,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            ),
                            color = primaryAccent
                        )
                    } else {
                        Text(
                            text = when (status) {
                                ConnectionStatus.CONNECTED -> "ON"
                                ConnectionStatus.CONNECTING -> "..."
                                ConnectionStatus.DISCONNECTING -> "..."
                                ConnectionStatus.ERROR -> "RETRY"
                                ConnectionStatus.DISCONNECTED -> "START"
                            },
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 2.sp
                            ),
                            color = primaryAccent
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Status Text Label
        Text(
            text = when (status) {
                ConnectionStatus.CONNECTED -> Localization.securedAndConnected(language)
                ConnectionStatus.CONNECTING -> Localization.connecting(language)
                ConnectionStatus.DISCONNECTING -> "Disconnecting..."
                ConnectionStatus.ERROR -> "Connection Failed (နှိပ်၍ ပြန်ချိတ်ပါ)"
                ConnectionStatus.DISCONNECTED -> Localization.tapToConnect(language)
            },
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
            color = if (isConnected) CyberGreen else if (isConnecting) ShweGold else NeonCyan,
            textAlign = TextAlign.Center
        )
    }
}
