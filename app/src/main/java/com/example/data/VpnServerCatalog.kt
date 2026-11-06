package com.example.data

import com.example.model.BankingBypassApp
import com.example.model.VpnServer

object VpnServerCatalog {

    val defaultServers = listOf(
        VpnServer(
            id = "sg_01",
            name = "Singapore - Fast Lane 01",
            myanmarName = "စင်ကာပူ - အမြန်လမ်း (အနိမ့်ဆုံး Ping)",
            country = "Singapore",
            city = "Singapore Central",
            flagEmoji = "🇸🇬",
            host = "sg01.shwevpn.net",
            port = 51820,
            pingMs = 28,
            loadPercent = 34,
            isRecommended = true,
            isMyanmarFastLane = true,
            isGamingOptimized = true,
            isStreamingOptimized = true,
            exitIp = "139.180.208.45"
        ),
        VpnServer(
            id = "sg_02",
            name = "Singapore - MLBB / Gaming 02",
            myanmarName = "စင်ကာပူ - MLBB / ဂိမ်းဆာဗာ",
            country = "Singapore",
            city = "Jurong Edge",
            flagEmoji = "🇸🇬",
            host = "sg02.shwevpn.net",
            port = 51820,
            pingMs = 24,
            loadPercent = 42,
            isRecommended = true,
            isMyanmarFastLane = true,
            isGamingOptimized = true,
            isStreamingOptimized = false,
            exitIp = "139.180.209.12"
        ),
        VpnServer(
            id = "sg_03",
            name = "Singapore - FB & Telegram Turbo",
            myanmarName = "စင်ကာပူ - FB / Telegram အထူးလိုင်း",
            country = "Singapore",
            city = "Changi",
            flagEmoji = "🇸🇬",
            host = "sg03.shwevpn.net",
            port = 443,
            pingMs = 32,
            loadPercent = 29,
            isRecommended = true,
            isMyanmarFastLane = true,
            isGamingOptimized = false,
            isStreamingOptimized = true,
            exitIp = "139.180.210.88"
        ),
        VpnServer(
            id = "th_01",
            name = "Thailand - Bangkok Direct 01",
            myanmarName = "ထိုင်း - ဘန်ကောက် ကုန်းတွင်းဖိုင်ဘာ",
            country = "Thailand",
            city = "Bangkok",
            flagEmoji = "🇹🇭",
            host = "th01.shwevpn.net",
            port = 51820,
            pingMs = 38,
            loadPercent = 38,
            isRecommended = true,
            isMyanmarFastLane = true,
            isGamingOptimized = true,
            isStreamingOptimized = true,
            exitIp = "119.59.100.22"
        ),
        VpnServer(
            id = "th_02",
            name = "Thailand - TikTok / YouTube 02",
            myanmarName = "ထိုင်း - ဗီဒီယိုကြည့် အမြန်လိုင်း",
            country = "Thailand",
            city = "Bangkok",
            flagEmoji = "🇹🇭",
            host = "th02.shwevpn.net",
            port = 443,
            pingMs = 42,
            loadPercent = 45,
            isRecommended = true,
            isMyanmarFastLane = true,
            isGamingOptimized = false,
            isStreamingOptimized = true,
            exitIp = "119.59.101.44"
        ),
        VpnServer(
            id = "my_01",
            name = "Malaysia - Kuala Lumpur Stable",
            myanmarName = "မလေးရှား - ကွာလာလမ်ပူ တည်ငြိမ်လိုင်း",
            country = "Malaysia",
            city = "Kuala Lumpur",
            flagEmoji = "🇲🇾",
            host = "my01.shwevpn.net",
            port = 51820,
            pingMs = 48,
            loadPercent = 26,
            isRecommended = false,
            isMyanmarFastLane = true,
            isGamingOptimized = true,
            isStreamingOptimized = false,
            exitIp = "103.253.24.18"
        ),
        VpnServer(
            id = "jp_01",
            name = "Japan - Tokyo CDN Express",
            myanmarName = "ဂျပန် - တိုကျို မြန်နှုန်းမြင့်",
            country = "Japan",
            city = "Tokyo",
            flagEmoji = "🇯🇵",
            host = "jp01.shwevpn.net",
            port = 51820,
            pingMs = 76,
            loadPercent = 51,
            isRecommended = false,
            isMyanmarFastLane = false,
            isGamingOptimized = false,
            isStreamingOptimized = true,
            exitIp = "167.179.94.30"
        ),
        VpnServer(
            id = "us_01",
            name = "United States - Los Angeles Speed",
            myanmarName = "အမေရိကန် - လော့စ်အိန်ဂျလိစ် (LA)",
            country = "United States",
            city = "Los Angeles",
            flagEmoji = "🇺🇸",
            host = "us01.shwevpn.net",
            port = 51820,
            pingMs = 158,
            loadPercent = 33,
            isRecommended = false,
            isMyanmarFastLane = false,
            isGamingOptimized = false,
            isStreamingOptimized = true,
            exitIp = "198.199.110.15"
        ),
        VpnServer(
            id = "us_02",
            name = "United States - New York Global",
            myanmarName = "အမေရိကန် - နယူးယောက် (NY)",
            country = "United States",
            city = "New York",
            flagEmoji = "🇺🇸",
            host = "us02.shwevpn.net",
            port = 443,
            pingMs = 175,
            loadPercent = 40,
            isRecommended = false,
            isMyanmarFastLane = false,
            isGamingOptimized = false,
            isStreamingOptimized = false,
            exitIp = "165.227.18.99"
        ),
        VpnServer(
            id = "de_01",
            name = "Germany - Frankfurt Privacy Shield",
            myanmarName = "ဂျာမနီ - ဖရန့်ဖတ် လုံခြုံရေးမြင့်",
            country = "Germany",
            city = "Frankfurt",
            flagEmoji = "🇩🇪",
            host = "de01.shwevpn.net",
            port = 51820,
            pingMs = 138,
            loadPercent = 22,
            isRecommended = false,
            isMyanmarFastLane = false,
            isGamingOptimized = false,
            isStreamingOptimized = false,
            exitIp = "159.65.120.70"
        ),
        VpnServer(
            id = "uk_01",
            name = "United Kingdom - London Hub",
            myanmarName = "ယူကေ - လန်ဒန် ဆာဗာ",
            country = "United Kingdom",
            city = "London",
            flagEmoji = "🇬🇧",
            host = "uk01.shwevpn.net",
            port = 51820,
            pingMs = 152,
            loadPercent = 35,
            isRecommended = false,
            isMyanmarFastLane = false,
            isGamingOptimized = false,
            isStreamingOptimized = true,
            exitIp = "178.62.10.82"
        ),
        VpnServer(
            id = "au_01",
            name = "Australia - Sydney Express",
            myanmarName = "သြစတြေးလျ - ဆစ်ဒနီ ဆာဗာ",
            country = "Australia",
            city = "Sydney",
            flagEmoji = "🇦🇺",
            host = "au01.shwevpn.net",
            port = 51820,
            pingMs = 114,
            loadPercent = 28,
            isRecommended = false,
            isMyanmarFastLane = false,
            isGamingOptimized = false,
            isStreamingOptimized = false,
            exitIp = "139.180.170.21"
        )
    )

    val defaultMyanmarBankingBypassApps = listOf(
        BankingBypassApp(
            name = "KBZPay (KPay)",
            packageName = "com.kbzbank.kpay",
            isEnabled = true,
            note = "KBZPay အကောင့် lock မကျစေရန် VPN အပြင်ဘက်မှ သွားစေပါသည်"
        ),
        BankingBypassApp(
            name = "Wave Money (WavePay)",
            packageName = "com.wavemoney.wavepay",
            isEnabled = true,
            note = "Wave Money လွှဲပြောင်းမှုများ ပုံမှန်အတိုင်း သုံးနိုင်စေပါသည်"
        ),
        BankingBypassApp(
            name = "AYA Pay",
            packageName = "com.ayabank.ayapay",
            isEnabled = true,
            note = "AYA Bank ဝန်ဆောင်မှုများကို တိုက်ရိုက်ဆက်သွယ်စေပါသည်"
        ),
        BankingBypassApp(
            name = "CB Pay",
            packageName = "com.cbbank.cbpay",
            isEnabled = true,
            note = "CB Bank အကောင့်ကို အန္တရာယ်ကင်းစွာ သုံးနိုင်စေပါသည်"
        ),
        BankingBypassApp(
            name = "uabpay",
            packageName = "com.uab.uabpay",
            isEnabled = true,
            note = "uab bank ငွေပေးချေမှုစနစ်"
        ),
        BankingBypassApp(
            name = "OnePay",
            packageName = "com.aggademo.onepay",
            isEnabled = true,
            note = "AGD OnePay ဝန်ဆောင်မှု"
        ),
        BankingBypassApp(
            name = "A Bank Mobile",
            packageName = "com.abank.mbanking",
            isEnabled = true,
            note = "A Bank မိုဘိုင်းဘဏ်စနစ်"
        ),
        BankingBypassApp(
            name = "MAB Mobile Banking",
            packageName = "com.mab.mobilebanking",
            isEnabled = true,
            note = "MAB Bank တိုက်ရိုက်ဆက်သွယ်မှု"
        )
    )
}
