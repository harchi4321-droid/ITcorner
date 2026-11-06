package com.example.ui.util

import com.example.model.AppLanguage

object Localization {

    fun appTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "ITCorner VPN (မြန်မာပြည် အမြန်လမ်း)"
        AppLanguage.ENGLISH -> "ITCorner VPN (Myanmar Fast Lane)"
    }

    fun tapToConnect(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "ချိတ်ဆက်ရန် နှိပ်ပါ"
        AppLanguage.ENGLISH -> "TAP TO CONNECT"
    }

    fun connecting(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "ချိတ်ဆက်နေပါသည်..."
        AppLanguage.ENGLISH -> "CONNECTING..."
    }

    fun securedAndConnected(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "လုံခြုံစွာ ချိတ်ဆက်ထားသည်"
        AppLanguage.ENGLISH -> "CONNECTED & SECURED"
    }

    fun tapToDisconnect(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "ချိတ်ဆက်မှု ဖြတ်ရန် နှိပ်ပါ"
        AppLanguage.ENGLISH -> "TAP TO DISCONNECT"
    }

    fun myanmarFastLaneBadge(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "🇲🇲 မြန်မာပြည်အတွက် သီးသန့် အကောင်းဆုံးလိုင်း"
        AppLanguage.ENGLISH -> "🇲🇲 Myanmar Direct Ultra-Low Latency"
    }

    fun bankingBypassActive(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "🛡️ KPay, WavePay စသည့် ဘဏ် App များ ပုံမှန်အတိုင်း သုံးနိုင်ပါသည်"
        AppLanguage.ENGLISH -> "🛡️ KBZPay & WavePay Bypass Active (Split Tunneling)"
    }

    fun downloadSpeed(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "ဒေါင်းလုဒ် (Download)"
        AppLanguage.ENGLISH -> "Download Speed"
    }

    fun uploadSpeed(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "အပ်လုဒ် (Upload)"
        AppLanguage.ENGLISH -> "Upload Speed"
    }

    fun duration(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "ချိတ်ဆက်ချိန်"
        AppLanguage.ENGLISH -> "Duration"
    }

    fun publicIp(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "ကာကွယ်ထားသော IP"
        AppLanguage.ENGLISH -> "Protected IP"
    }

    fun autoBest(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "အနိမ့်ဆုံး Ping အလိုအလျောက် ရွေးချယ်မည်"
        AppLanguage.ENGLISH -> "Auto-Select Fastest Low-Ping"
    }

    fun tabHome(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "ပင်မ"
        AppLanguage.ENGLISH -> "VPN"
    }

    fun tabServers(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "ဆာဗာများ"
        AppLanguage.ENGLISH -> "Servers"
    }

    fun tabSpeedTest(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "လိုင်းစစ်ရန်"
        AppLanguage.ENGLISH -> "Speed Test"
    }

    fun tabSettings(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "ဆက်တင်"
        AppLanguage.ENGLISH -> "Settings"
    }

    fun startSpeedTest(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "လိုင်းအမြန်နှုန်း စတင်စစ်ဆေးမည်"
        AppLanguage.ENGLISH -> "START SPEED TEST"
    }

    fun testingInProgress(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "အမြန်နှုန်း တိုင်းတာနေပါသည်..."
        AppLanguage.ENGLISH -> "TESTING SPEED..."
    }

    fun ping(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "တုံ့ပြန်မှု (Ping)"
        AppLanguage.ENGLISH -> "Ping"
    }

    fun jitter(lang: AppLanguage) = when (lang) {
        AppLanguage.MYANMAR -> "လိုင်းငြိမ်သက်မှု (Jitter)"
        AppLanguage.ENGLISH -> "Jitter"
    }
}
