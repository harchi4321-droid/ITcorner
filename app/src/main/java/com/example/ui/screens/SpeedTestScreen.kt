package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppLanguage
import com.example.model.SpeedTestStage
import com.example.ui.VpnUiState
import com.example.ui.VpnViewModel
import com.example.ui.theme.CyberGreen
import com.example.ui.theme.MidnightBg
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.ShweGold
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceCardBorder
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.util.Localization
import java.util.Locale
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SpeedTestScreen(
    uiState: VpnUiState,
    viewModel: VpnViewModel,
    modifier: Modifier = Modifier
) {
    val language = uiState.session.language
    val speedResult by viewModel.speedTestState.collectAsState()
    val server = uiState.session.currentServer

    // Speedometer active value
    val activeSpeed = when (speedResult.stage) {
        SpeedTestStage.TESTING_DOWNLOAD -> speedResult.downloadMbps
        SpeedTestStage.TESTING_UPLOAD -> speedResult.uploadMbps
        SpeedTestStage.COMPLETED -> speedResult.downloadMbps
        else -> 0f
    }

    val animatedSpeed by animateFloatAsState(
        targetValue = activeSpeed,
        animationSpec = tween(durationMillis = 300),
        label = "needle_speed"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MidnightBg)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = if (language == AppLanguage.MYANMAR) "လိုင်းအမြန်နှုန်း တိုင်းတာမှု" else "Live Speed Test",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = TextPrimary
                )
                Text(
                    text = if (language == AppLanguage.MYANMAR) "မြန်မာ ISP များနှင့် VPN တိုက်ရိုက်အမြန်နှုန်း စစ်ဆေးရန်" else "Measure real-time bandwidth & latency",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Speedometer Arc Gauge
        Box(
            modifier = Modifier
                .size(260.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(240.dp)) {
                val strokeWidth = 16.dp.toPx()
                val radius = (size.minDimension - strokeWidth) / 2
                val centerOffset = Offset(size.width / 2, size.height / 2)

                // Background track arc (135 degrees to 405 degrees -> 270 deg span)
                drawArc(
                    color = SurfaceCardBorder,
                    startAngle = 135f,
                    sweepAngle = 270f,
                    useCenter = false,
                    topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                    size = Size(size.width - strokeWidth, size.height - strokeWidth),
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )

                // Active speed sweep
                val fraction = (animatedSpeed / 100f).coerceIn(0f, 1f)
                val activeSweep = fraction * 270f

                drawArc(
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            NeonCyan,
                            CyberGreen,
                            ShweGold,
                            NeonCyan
                        )
                    ),
                    startAngle = 135f,
                    sweepAngle = activeSweep,
                    useCenter = false,
                    topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                    size = Size(size.width - strokeWidth, size.height - strokeWidth),
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )

                // Needle indicator dot
                val needleAngle = Math.toRadians((135.0 + activeSweep).toDouble())
                val needleX = centerOffset.x + (radius * cos(needleAngle)).toFloat()
                val needleY = centerOffset.y + (radius * sin(needleAngle)).toFloat()

                drawCircle(
                    color = Color.White,
                    radius = 8.dp.toPx(),
                    center = Offset(needleX, needleY)
                )
                drawCircle(
                    color = NeonCyan,
                    radius = 5.dp.toPx(),
                    center = Offset(needleX, needleY)
                )
            }

            // Center speed readout
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = String.format(Locale.US, "%.1f", animatedSpeed),
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Black,
                        letterSpacing = (-1).sp
                    ),
                    color = TextPrimary
                )
                Text(
                    text = "Mbps",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = NeonCyan
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = when (speedResult.stage) {
                        SpeedTestStage.TESTING_PING -> if (language == AppLanguage.MYANMAR) "Ping တိုင်းတာနေသည်..." else "Testing Ping..."
                        SpeedTestStage.TESTING_DOWNLOAD -> if (language == AppLanguage.MYANMAR) "ဒေါင်းလုဒ် တိုင်းတာနေသည်..." else "Testing Download..."
                        SpeedTestStage.TESTING_UPLOAD -> if (language == AppLanguage.MYANMAR) "အပ်လုဒ် တိုင်းတာနေသည်..." else "Testing Upload..."
                        SpeedTestStage.COMPLETED -> if (language == AppLanguage.MYANMAR) "စစ်ဆေးမှု ပြီးဆုံးပါပြီ" else "Test Completed"
                        SpeedTestStage.IDLE -> if (language == AppLanguage.MYANMAR) "အသင့်ဖြစ်နေပါသည်" else "Ready to Test"
                    },
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = if (speedResult.stage == SpeedTestStage.COMPLETED) CyberGreen else ShweGold
                )
            }
        }

        if (speedResult.isRunning) {
            LinearProgressIndicator(
                progress = { speedResult.progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = NeonCyan,
                trackColor = SurfaceCardBorder,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 4 Metrics Grid (Ping, Jitter, Download, Upload)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MetricCard(
                title = Localization.ping(language),
                value = "${speedResult.pingMs} ms",
                icon = Icons.Default.Bolt,
                color = CyberGreen,
                modifier = Modifier.weight(1f)
            )
            MetricCard(
                title = Localization.jitter(language),
                value = "${speedResult.jitterMs} ms",
                icon = Icons.Default.NetworkCheck,
                color = ShweGold,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MetricCard(
                title = Localization.downloadSpeed(language),
                value = String.format(Locale.US, "%.1f Mbps", speedResult.downloadMbps),
                icon = Icons.Default.ArrowDownward,
                color = NeonCyan,
                modifier = Modifier.weight(1f)
            )
            MetricCard(
                title = Localization.uploadSpeed(language),
                value = String.format(Locale.US, "%.1f Mbps", speedResult.uploadMbps),
                icon = Icons.Default.ArrowUpward,
                color = Color(0xFFB388FF),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Tested Server Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceCard)
                .border(1.dp, SurfaceCardBorder, RoundedCornerShape(14.dp))
                .padding(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = server.flagEmoji, fontSize = 24.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = server.name,
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = TextPrimary
                        )
                        Text(
                            text = "Edge Node: ${server.city} (${server.host})",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(CyberGreen.copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "READY",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = CyberGreen
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Start Test Button
        Button(
            onClick = { viewModel.runSpeedTest() },
            enabled = !speedResult.isRunning,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .clip(RoundedCornerShape(16.dp))
                .testTag("start_speed_test_button"),
            colors = ButtonDefaults.buttonColors(
                containerColor = NeonCyan,
                contentColor = MidnightBg,
                disabledContainerColor = SurfaceCardBorder,
                disabledContentColor = TextMuted
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Speed,
                    contentDescription = "Run speed test",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = if (speedResult.isRunning)
                        Localization.testingInProgress(language)
                    else
                        Localization.startSpeedTest(language),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun MetricCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceCardBorder)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(color.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = color,
                        modifier = Modifier.size(14.dp)
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = TextPrimary
            )
        }
    }
}
