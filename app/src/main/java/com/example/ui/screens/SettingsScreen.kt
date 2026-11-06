package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.Color
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
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Dns
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppLanguage
import com.example.model.DnsOption
import com.example.model.VpnProtocol
import com.example.ui.VpnUiState
import com.example.ui.VpnViewModel
import com.example.ui.theme.CyberGreen
import com.example.ui.theme.CyberRed
import com.example.ui.theme.MidnightBg
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.ShweGold
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceCardBorder
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun SettingsScreen(
    uiState: VpnUiState,
    viewModel: VpnViewModel,
    modifier: Modifier = Modifier
) {
    val session = uiState.session
    val language = session.language
    var showBypassAppsList by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MidnightBg)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = if (language == AppLanguage.MYANMAR) "ဆက်တင်များနှင့် လုံခြုံရေး" else "Settings & Security",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = TextPrimary
        )
        Text(
            text = if (language == AppLanguage.MYANMAR) "မြန်မာပြည်အတွက် စိတ်ကြိုက်ပြင်ဆင်နိုင်သော အဆင့်မြင့်စနစ်များ" else "Custom routing, protocols & security for Myanmar",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Section 1: Myanmar Banking App Split-Tunneling
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceCardBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(ShweGold.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountBalance,
                                contentDescription = "Banking Bypass",
                                tint = ShweGold,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = if (language == AppLanguage.MYANMAR) "မြန်မာဘဏ် App များ ချွင်းချက် (Bypass)" else "Myanmar Banking Bypass",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = TextPrimary
                            )
                            Text(
                                text = if (language == AppLanguage.MYANMAR) "KPay, WavePay များ VPN အပြင်မှ သီးသန့်သွားမည်" else "Routes KBZPay, WavePay outside VPN tunnel",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary
                            )
                        }
                    }

                    Switch(
                        checked = session.bypassBankingEnabled,
                        onCheckedChange = { viewModel.setBypassBanking(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MidnightBg,
                            checkedTrackColor = ShweGold,
                            uncheckedThumbColor = TextMuted,
                            uncheckedTrackColor = SurfaceCardBorder
                        ),
                        modifier = Modifier.testTag("banking_bypass_switch")
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(MidnightBg.copy(alpha = 0.6f))
                        .padding(10.dp)
                ) {
                    Text(
                        text = if (language == AppLanguage.MYANMAR)
                            "💡 အသုံးဝင်ပုံ- VPN ဖွင့်ထားချိန်တွင် KPay သို့မဟုတ် Wave Money သုံးပါက Account Lock ကျခြင်း၊ အကောင့်စစ်ဆေးခံရခြင်းမှ ကင်းဝေးစေပါသည်။"
                        else
                            "💡 Why useful: Prevents local mobile banking accounts from being flagged or temporarily restricted while VPN is active.",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                        color = TextMuted
                    )
                }

                if (session.bypassBankingEnabled) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = if (showBypassAppsList)
                            (if (language == AppLanguage.MYANMAR) "ဘဏ်စာရင်းများကို ပြန်သိမ်းမည် ▲" else "Hide Banking Apps List ▲")
                        else
                            (if (language == AppLanguage.MYANMAR) "ပါဝင်သော ဘဏ် App များ စာရင်းကြည့်မည် (${session.bypassApps.count { it.isEnabled }} ခု ဖွင့်ထားသည်) ▼" else "View Managed Banking Apps (${session.bypassApps.count { it.isEnabled }} enabled) ▼"),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = NeonCyan
                        ),
                        modifier = Modifier
                            .clickable { showBypassAppsList = !showBypassAppsList }
                            .padding(vertical = 4.dp)
                    )

                    if (showBypassAppsList) {
                        Spacer(modifier = Modifier.height(8.dp))
                        session.bypassApps.forEach { bypassApp ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = bypassApp.name,
                                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                                        color = TextPrimary
                                    )
                                    Text(
                                        text = bypassApp.note,
                                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                        color = TextSecondary
                                    )
                                }
                                Switch(
                                    checked = bypassApp.isEnabled,
                                    onCheckedChange = { viewModel.toggleBypassApp(bypassApp.packageName) },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = MidnightBg,
                                        checkedTrackColor = CyberGreen,
                                        uncheckedThumbColor = TextMuted,
                                        uncheckedTrackColor = SurfaceCardBorder
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Section: Routing Mode
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceCardBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(ShweGold.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bolt,
                            contentDescription = "Routing Mode",
                            tint = ShweGold,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = if (language == AppLanguage.MYANMAR) "လိုင်းဆွဲအား & လမ်းကြောင်းစနစ် (Routing Mode)" else "VPN Routing Mode",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = TextPrimary
                        )
                        Text(
                            text = if (language == AppLanguage.MYANMAR) "လိုင်းအကောင်းဆုံးနှင့် အပိတ်အဆို့ကျော်လွှားမှုအတွက်" else "Optimized for Myanmar networks & anti-censorship",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                com.example.model.VpnRoutingMode.values().forEach { mode ->
                    val isSelected = session.routingMode == mode
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) ShweGold.copy(alpha = 0.1f) else Color.Transparent)
                            .clickable { viewModel.selectRoutingMode(mode) }
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = { viewModel.selectRoutingMode(mode) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = ShweGold,
                                unselectedColor = SurfaceCardBorder
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = if (language == AppLanguage.MYANMAR) mode.titleMyanmar else mode.titleEnglish,
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                color = if (isSelected) ShweGold else TextPrimary
                            )
                            Text(
                                text = if (language == AppLanguage.MYANMAR) mode.descMyanmar else mode.descEnglish,
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                                color = TextSecondary
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Section 2: VPN Protocol Selection
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceCardBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(NeonCyan.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.VpnKey,
                            contentDescription = "Protocol",
                            tint = NeonCyan,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = if (language == AppLanguage.MYANMAR) "ချိတ်ဆက်မှု စနစ် (Protocol)" else "Tunneling Protocol",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = TextPrimary
                        )
                        Text(
                            text = if (language == AppLanguage.MYANMAR) "မိမိအသုံးပြုမည့် ပုံစံအလိုက် ရွေးချယ်နိုင်ပါသည်" else "Choose tunneling mode based on your needs",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                VpnProtocol.values().forEach { protocol ->
                    val isSelected = session.selectedProtocol == protocol
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) NeonCyan.copy(alpha = 0.1f) else Color.Transparent)
                            .clickable { viewModel.selectProtocol(protocol) }
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = { viewModel.selectProtocol(protocol) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = NeonCyan,
                                unselectedColor = SurfaceCardBorder
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = protocol.displayName,
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                    color = if (isSelected) NeonCyan else TextPrimary
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = protocol.portType,
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                    color = ShweGold
                                )
                            }
                            Text(
                                text = if (language == AppLanguage.MYANMAR) protocol.myanmarDesc else protocol.englishDesc,
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                                color = TextSecondary
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Section 3: DNS Encryption
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceCardBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(CyberGreen.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Dns,
                            contentDescription = "DNS",
                            tint = CyberGreen,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = if (language == AppLanguage.MYANMAR) "လုံခြုံသော DNS (Encrypted DNS)" else "Encrypted DNS Server",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = TextPrimary
                        )
                        Text(
                            text = if (language == AppLanguage.MYANMAR) "ISP များ၏ ဝဘ်ဆိုဒ်ပိတ်ဆို့မှုများကို လွတ်ကင်းစေသည်" else "Prevents DNS hijacking and unblocks domain names",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                DnsOption.values().forEach { dnsOption ->
                    val isSelected = session.selectedDns == dnsOption
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) CyberGreen.copy(alpha = 0.1f) else Color.Transparent)
                            .clickable { viewModel.selectDns(dnsOption) }
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = { viewModel.selectDns(dnsOption) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = CyberGreen,
                                unselectedColor = SurfaceCardBorder
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = dnsOption.title,
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                color = if (isSelected) CyberGreen else TextPrimary
                            )
                            Text(
                                text = if (language == AppLanguage.MYANMAR) dnsOption.descMyanmar else dnsOption.descEnglish,
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                                color = TextSecondary
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Section 4: Kill Switch & Language
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceCardBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Kill Switch
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(CyberRed.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Kill Switch",
                                tint = CyberRed,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Kill Switch",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = TextPrimary
                            )
                            Text(
                                text = if (language == AppLanguage.MYANMAR) "VPN ပြုတ်သွားပါက အင်တာနက် အလိုအလျောက်ပိတ်မည်" else "Block internet if VPN connection drops",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary
                            )
                        }
                    }

                    Switch(
                        checked = session.killSwitchEnabled,
                        onCheckedChange = { viewModel.setKillSwitch(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MidnightBg,
                            checkedTrackColor = CyberRed,
                            uncheckedThumbColor = TextMuted,
                            uncheckedTrackColor = SurfaceCardBorder
                        ),
                        modifier = Modifier.testTag("kill_switch_toggle")
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = SurfaceCardBorder
                )

                // Language Toggle Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.toggleLanguage() },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(NeonCyan.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = "Language",
                                tint = NeonCyan,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = if (language == AppLanguage.MYANMAR) "အက်ပ်ဘာသာစကား" else "App Language",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = TextPrimary
                            )
                            Text(
                                text = if (language == AppLanguage.MYANMAR) "လက်ရှိ: မြန်မာစာ (Burmese)" else "Current: English",
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
                            text = if (language == AppLanguage.MYANMAR) "Change to EN" else "မြန်မာစာသို့ ပြောင်းမည်",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = NeonCyan
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Section 5: No-Logs Policy
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceCard)
                .border(1.dp, SurfaceCardBorder, RoundedCornerShape(14.dp))
                .padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = "Zero Logs",
                    tint = CyberGreen,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = if (language == AppLanguage.MYANMAR) "၁၀၀% လုံခြုံစိတ်ချရသော Zero-Logs Policy" else "100% Strict Zero-Logs Guarantee",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = CyberGreen
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (language == AppLanguage.MYANMAR)
                            "ITCorner VPN သည် သုံးစွဲသူများ၏ မည်သည့် ရှာဖွေမှုမှတ်တမ်း၊ အသုံးပြုသည့် ဝဘ်ဆိုဒ်နှင့် ကိုယ်ရေးအချက်အလက်ကိုမျှ လုံးဝမှတ်တမ်းတင်သိမ်းဆည်းခြင်းမရှိပါ။ သင်၏အင်တာနက်လုံခြုံမှုကို အမြဲဦးစားပေးပါသည်။"
                        else
                            "ITCorner VPN does not collect, monitor, or log your browsing history, traffic destination, or DNS queries. Your internet freedom and privacy are fully protected.",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                        color = TextMuted
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
