package com.example.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppLanguage
import com.example.model.VpnServer
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServerListScreen(
    uiState: VpnUiState,
    viewModel: VpnViewModel,
    modifier: Modifier = Modifier
) {
    val language = uiState.session.language
    val currentSelected = uiState.session.currentServer

    val infiniteTransition = rememberInfiniteTransition(label = "refresh_spin")
    val spinAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing)
        ),
        label = "spin_angle"
    )

    // Filter server list based on tab and search
    val filteredServers = uiState.serverList.filter { server ->
        val query = uiState.serverSearchQuery.trim().lowercase()
        val matchesQuery = query.isEmpty() ||
                server.name.lowercase().contains(query) ||
                server.country.lowercase().contains(query) ||
                server.city.lowercase().contains(query) ||
                server.myanmarName.lowercase().contains(query)

        val matchesFilter = when (uiState.selectedServerFilter) {
            "RECOMMENDED" -> server.isRecommended
            "MYANMAR_LANE" -> server.isMyanmarFastLane
            "GAMING" -> server.isGamingOptimized || server.isStreamingOptimized
            else -> true
        }

        matchesQuery && matchesFilter
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MidnightBg)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = if (language == AppLanguage.MYANMAR) "ဆာဗာများ ရွေးချယ်ရန်" else "Select Server",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = TextPrimary
                )
                Text(
                    text = if (language == AppLanguage.MYANMAR) "မြန်မာပြည်အတွက် အနိမ့်ဆုံး Ping ဆာဗာများ" else "Ultra-low ping servers for Myanmar",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }

            IconButton(
                onClick = { viewModel.refreshServerPings() },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(SurfaceCard)
                    .testTag("refresh_pings_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh pings",
                    tint = NeonCyan,
                    modifier = Modifier.rotate(if (uiState.isPingingServers) spinAngle else 0f)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Auto Select Fastest Banner
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.autoSelectFastestServer() }
                .testTag("auto_select_fastest_card"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, NeonCyan.copy(alpha = 0.4f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(NeonCyan, ShweGold)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bolt,
                            contentDescription = "Fastest",
                            tint = MidnightBg,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = if (language == AppLanguage.MYANMAR) "အမြန်ဆုံးဆာဗာ အလိုအလျောက်ရွေးမည်" else "Auto-Select Fastest Server",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = TextPrimary
                        )
                        Text(
                            text = if (language == AppLanguage.MYANMAR) "စင်ကာပူ / ထိုင်း အနိမ့်ဆုံး Ping သို့ ချက်ချင်းချိတ်ပေးမည်" else "Connects to lowest latency edge node",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(NeonCyan.copy(alpha = 0.15f))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "FASTEST",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = NeonCyan
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Search Input
        OutlinedTextField(
            value = uiState.serverSearchQuery,
            onValueChange = { viewModel.setSearchQuery(it) },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("server_search_input"),
            placeholder = {
                Text(
                    text = if (language == AppLanguage.MYANMAR) "နိုင်ငံ (သို့) မြို့အမည် ရှာဖွေပါ..." else "Search country or city...",
                    color = TextMuted,
                    fontSize = 14.sp
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = TextSecondary
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = SurfaceCard,
                unfocusedContainerColor = SurfaceCard,
                focusedBorderColor = NeonCyan,
                unfocusedBorderColor = SurfaceCardBorder,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Filter Chips Row
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                ServerFilterChip(
                    selected = uiState.selectedServerFilter == "ALL",
                    label = if (language == AppLanguage.MYANMAR) "အားလုံး" else "All",
                    onClick = { viewModel.setServerFilter("ALL") }
                )
            }
            item {
                ServerFilterChip(
                    selected = uiState.selectedServerFilter == "MYANMAR_LANE",
                    label = if (language == AppLanguage.MYANMAR) "🇲🇲 မြန်မာ အမြန်လမ်း (SG & TH)" else "🇲🇲 Myanmar Direct",
                    onClick = { viewModel.setServerFilter("MYANMAR_LANE") }
                )
            }
            item {
                ServerFilterChip(
                    selected = uiState.selectedServerFilter == "RECOMMENDED",
                    label = if (language == AppLanguage.MYANMAR) "အကြံပြု (Recommended)" else "Recommended",
                    onClick = { viewModel.setServerFilter("RECOMMENDED") }
                )
            }
            item {
                ServerFilterChip(
                    selected = uiState.selectedServerFilter == "GAMING",
                    label = if (language == AppLanguage.MYANMAR) "ဂိမ်း & ဗီဒီယို" else "Gaming & Stream",
                    onClick = { viewModel.setServerFilter("GAMING") }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Server List Items
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredServers, key = { it.id }) { server ->
                val isSelected = server.id == currentSelected.id
                ServerItemCard(
                    server = server,
                    isSelected = isSelected,
                    language = language,
                    onSelect = { viewModel.selectServer(server) }
                )
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ServerFilterChip(
    selected: Boolean,
    label: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (selected) NeonCyan.copy(alpha = 0.2f) else SurfaceCard)
            .border(
                1.dp,
                if (selected) NeonCyan else SurfaceCardBorder,
                RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 7.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
            ),
            color = if (selected) NeonCyan else TextSecondary
        )
    }
}

@Composable
fun ServerItemCard(
    server: VpnServer,
    isSelected: Boolean,
    language: AppLanguage,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .testTag("server_item_${server.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) SurfaceCard.copy(alpha = 0.95f) else SurfaceCard
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isSelected) CyberGreen else SurfaceCardBorder
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = server.flagEmoji,
                        fontSize = 28.sp
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (language == AppLanguage.MYANMAR) server.myanmarName else server.name,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = TextPrimary
                            )
                        }
                        Text(
                            text = "${server.country} • ${server.city}",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                }

                // Ping & Select Indicator
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val pingColor = when {
                        server.pingMs < 40 -> CyberGreen
                        server.pingMs < 90 -> NeonCyan
                        server.pingMs < 150 -> ShweGold
                        else -> TextMuted
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(pingColor.copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "${server.pingMs} ms",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = pingColor
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(CyberGreen),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = MidnightBg,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Load and Badges
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    if (server.isMyanmarFastLane) {
                        BadgePill(text = "🇲🇲 Fast Lane", color = ShweGold)
                    }
                    if (server.isGamingOptimized) {
                        BadgePill(text = "🎮 Gaming / MLBB", color = NeonCyan)
                    }
                    if (server.isStreamingOptimized) {
                        BadgePill(text = "📺 Streaming", color = CyberGreen)
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Load: ${server.loadPercent}%",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                        color = TextMuted
                    )
                }
            }
        }
    }
}

@Composable
fun BadgePill(text: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(alpha = 0.12f))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp
            ),
            color = color
        )
    }
}
