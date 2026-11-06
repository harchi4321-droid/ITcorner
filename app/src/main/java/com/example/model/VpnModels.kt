package com.example.model

enum class ConnectionStatus {
    DISCONNECTED,
    CONNECTING,
    CONNECTED,
    DISCONNECTING,
    ERROR
}

enum class AppLanguage {
    MYANMAR,
    ENGLISH
}

data class VpnServer(
    val id: String,
    val name: String,
    val myanmarName: String,
    val country: String,
    val city: String,
    val flagEmoji: String,
    val host: String,
    val port: Int,
    var pingMs: Int,
    val loadPercent: Int,
    val isRecommended: Boolean = false,
    val isMyanmarFastLane: Boolean = false,
    val isGamingOptimized: Boolean = false,
    val isStreamingOptimized: Boolean = false,
    val exitIp: String = "192.241.142.75"
)

enum class VpnProtocol(
    val displayName: String,
    val myanmarDesc: String,
    val englishDesc: String,
    val portType: String
) {
    WIREGUARD(
        displayName = "Turbo WireGuard",
        myanmarDesc = "အလွန်မြန်ဆန်သော ခေတ်မီ UDP လှိုင်း - ဂိမ်းဆော့ရန်နှင့် အသံဖုန်းခေါ်ရန် အကောင်းဆုံးဖြစ်သည်",
        englishDesc = "Ultra-fast modern UDP tunnel, lowest latency for gaming and video calls",
        portType = "51820/UDP"
    ),
    STEALTH_TLS(
        displayName = "Stealth TLS (Anti-DPI)",
        myanmarDesc = "မြန်မာ ISP များ၏ Deep Packet Inspection (DPI) အပိတ်အဆို့များကို ကျော်လွှားနိုင်သော HTTPS အယောင်ဆောင်စနစ်",
        englishDesc = "Camouflages VPN traffic as regular HTTPS to bypass telecom DPI firewall blocks",
        portType = "443/TCP"
    ),
    SHADOWSOCKS(
        displayName = "Shadowsocks AEAD",
        myanmarDesc = "ပေါ့ပါးမြန်ဆန်ပြီး ဘက်ထရီစားသက်သာသော စစ်သုံးအဆင့် အင်ခရစ်ရှင်းလှိုင်း",
        englishDesc = "High-performance encrypted proxy, lightweight and battery efficient",
        portType = "8388/TCP"
    ),
    OPENVPN_FAST(
        displayName = "OpenVPN Turbo",
        myanmarDesc = "ကမ္ဘာ့အသုံးအများဆုံးနှင့် အလွန်ခိုင်မာလုံခြုံစိတ်ချရသော စံချိန်မီ VPN စနစ်",
        englishDesc = "Standard trusted open protocol with maximum stability and compatibility",
        portType = "1194/UDP"
    )
}

enum class DnsOption(
    val title: String,
    val descMyanmar: String,
    val descEnglish: String,
    val servers: List<String>
) {
    CLOUDFLARE(
        title = "Cloudflare 1.1.1.1",
        descMyanmar = "အမြန်ဆုံး DNS အမြန်နှုန်းနှင့် အချက်အလက်လုံခြုံမှု အပြည့်အဝရရှိစေသည်",
        descEnglish = "Fastest DNS resolution with strict privacy and no query logging",
        servers = listOf("1.1.1.1", "1.0.0.1")
    ),
    GOOGLE(
        title = "Google DNS 8.8.8.8",
        descMyanmar = "ကမ္ဘာပေါ်တွင် အယုံကြည်ရဆုံးနှင့် အလွန်တည်ငြိမ်သော Google Public DNS",
        descEnglish = "Worldwide reliable and stable resolution powered by Google",
        servers = listOf("8.8.8.8", "8.8.4.4")
    ),
    ADGUARD(
        title = "AdGuard DNS",
        descMyanmar = "ကြော်ငြာများ၊ မလိုလားအပ်သော Malware များနှင့် Phishing ဆိုဒ်များကို အလိုအလျောက် ပိတ်ဆို့ပေးသည်",
        descEnglish = "Automatically blocks intrusive ads, tracking, and phishing domains",
        servers = listOf("94.140.14.14", "94.140.15.15")
    )
}

data class BankingBypassApp(
    val name: String,
    val packageName: String,
    val isEnabled: Boolean,
    val note: String
)

data class SpeedTestResult(
    val pingMs: Int = 0,
    val jitterMs: Int = 0,
    val downloadMbps: Float = 0f,
    val uploadMbps: Float = 0f,
    val isRunning: Boolean = false,
    val stage: SpeedTestStage = SpeedTestStage.IDLE,
    val progress: Float = 0f
)

enum class SpeedTestStage {
    IDLE,
    TESTING_PING,
    TESTING_DOWNLOAD,
    TESTING_UPLOAD,
    COMPLETED
}

enum class VpnRoutingMode(
    val titleMyanmar: String,
    val titleEnglish: String,
    val descMyanmar: String,
    val descEnglish: String
) {
    FAST_ANTI_BLOCK(
        titleMyanmar = "အမြန်နှုန်း & အပိတ်အဆို့ကျော် Mode (Recommended)",
        titleEnglish = "Fast Lane & Anti-Censorship Mode (Recommended)",
        descMyanmar = "မြန်မာ ISP များ၏ DNS အပိတ်အဆို့များကို ကျော်လွှားပြီး လိုင်းဆွဲအားအကောင်းဆုံးဖြင့် ချောမွေ့စွာ သုံးနိုင်သည်",
        descEnglish = "Bypasses telecom DNS censorship with maximum line speed and zero drops"
    ),
    FULL_TUNNEL(
        titleMyanmar = "ကမ္ဘာလုံးဆိုင်ရာ Full Tunnel (0.0.0.0/0)",
        titleEnglish = "Global Full Tunnel (0.0.0.0/0)",
        descMyanmar = "စက်တစ်ခုလုံး၏ အင်တာနက်အသွားအလာအားလုံးကို လုံခြုံစွာ လှည့်ပေးသည်",
        descEnglish = "Routes all device network traffic through encrypted tunnel interface"
    )
}

