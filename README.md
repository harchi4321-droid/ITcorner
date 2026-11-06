# ITCorner VPN (🇲🇲 Myanmar High-Speed VPN)

မြန်မာပြည်အတွက် အထူးသီးသန့် လိုင်းဆွဲအားအကောင်းဆုံး၊ အနိမ့်ဆုံး Ping နှင့် လုံခြုံစိတ်ချရသော Android VPN Proxy App ဖြစ်ပါသည်။

---

## 🌟 အဓိက လုပ်ဆောင်ချက်များ (Key Features)

- ⚡ **Myanmar Fast Lane Routes**: စင်ကာပူ (🇸🇬 24-28ms) နှင့် ထိုင်း (🇹🇭 38-42ms) အနိမ့်ဆုံး Ping ဆာဗာများဖြင့် ဂိမ်းဆော့ခြင်း (MLBB, PUBG)၊ Facebook, Telegram, YouTube တို့ကို အထူးမြန်ဆန်စွာ သုံးနိုင်ခြင်း။
- 🛡️ **Anti-DPI & Anti-Censorship**: မြန်မာ ISP များ၏ DNS အပိတ်အဆို့များကို ကျော်လွှားနိုင်သော Fast Anti-Censorship Mode နှင့် Stealth TLS Protocol။
- 🏦 **Myanmar Banking Split Tunneling**: VPN ဖွင့်ထားသော်လည်း KBZPay (KPay), Wave Money, AYA Pay, CB Pay အသုံးပြုရာတွင် အကောင့် lock မကျစေရန် သီးသန့် Bypass လမ်းကြောင်း။
- 🚀 **Live Speed Test**: တိုက်ရိုက် Download/Upload Mbps, Ping (ms), Jitter (ms) တိုင်းတာပေးသည့် Speedometer။
- 🇲🇲 **Dual Language**: မြန်မာစာ / English လွယ်ကူစွာ ပြောင်းလဲနိုင်ခြင်း။

---

## 📱 GitHub ကနေ APK ထုတ်ယူနည်း (How to Export APK from GitHub)

### အဆင့် ၁ - GitHub သို့ Project Push လုပ်ခြင်း
1. Google AI Studio ၏ ညာဘက်အပေါ်ထောင့်ရှိ **GitHub** (သို့မဟုတ် Settings / Share / Export) ခလုတ်ကို နှိပ်ပါ။
2. မိမိ၏ GitHub Account နှင့် ချိတ်ဆက်ပြီး Repository အသစ်တစ်ခု ဖန်တီး၍ Push ပြုလုပ်ပါ။

### အဆင့် ၂ - GitHub Actions မှ APK အလိုအလျောက် ထုတ်ယူခြင်း
Project ကို GitHub သို့ ရောက်ရှိသွားသည်နှင့် ကျွန်ုပ်တို့ ထည့်သွင်းပေးထားသော `.github/workflows/build-apk.yml` က **အလိုအလျောက် APK တည်ဆောက် (Build) ပေးပါမည်**:
1. GitHub ရှိ မိမိ၏ Repository သို့ သွားပါ။
2. အပေါ်ဘက် Tab များထဲမှ **Actions** ကို နှိပ်ပါ။
3. **Build & Release ITCorner APK** အလုပ်ကို တွေ့ရပါမည်။ (သို့မဟုတ် **Run workflow** ခလုတ်ကို နှိပ်နိုင်ပါသည်)။
4. Build ပြီးဆုံးပါက အောက်ဘက်ရှိ **Artifacts** ကဏ္ဍတွင် **`ITCorner-VPN-Debug-APK`** ကို နှိပ်၍ ဖုန်းထဲသို့ ဒေါင်းလုဒ်ရယူနိုင်ပါသည်။

---

## 💻 Local စက်တွင် APK တည်ဆောက်နည်း (Local Build)

Android Studio သို့မဟုတ် Terminal မှ တိုက်ရိုက် Build လုပ်လိုပါက:

```bash
# Debug APK တည်ဆောက်ရန်
./gradlew assembleDebug

# Output APK တည်နေရာ:
# app/build/outputs/apk/debug/app-debug.apk
```

---

## 📄 လုံခြုံရေးနှင့် မူဝါဒ (Zero-Logs Guarantee)
ITCorner VPN သည် သုံးစွဲသူများ၏ မည်သည့် ကိုယ်ရေးအချက်အလက်နှင့် ရှာဖွေမှုမှတ်တမ်းကိုမျှ မှတ်တမ်းတင်သိမ်းဆည်းခြင်းမရှိပါ။ (100% Strict Zero-Logs).
