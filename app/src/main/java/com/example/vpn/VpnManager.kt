package com.example.vpn

import android.content.Context
import android.content.Intent
import com.example.data.VpnServerCatalog
import com.example.model.AppLanguage
import com.example.model.BankingBypassApp
import com.example.model.ConnectionStatus
import com.example.model.DnsOption
import com.example.model.VpnProtocol
import com.example.model.VpnServer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class VpnSessionState(
    val status: ConnectionStatus = ConnectionStatus.DISCONNECTED,
    val currentServer: VpnServer = VpnServerCatalog.defaultServers.first(),
    val durationSeconds: Long = 0L,
    val downloadSpeedBps: Long = 0L,
    val uploadSpeedBps: Long = 0L,
    val totalDownloadBytes: Long = 0L,
    val totalUploadBytes: Long = 0L,
    val selectedProtocol: VpnProtocol = VpnProtocol.WIREGUARD,
    val selectedDns: DnsOption = DnsOption.CLOUDFLARE,
    val killSwitchEnabled: Boolean = false,
    val bypassBankingEnabled: Boolean = true,
    val bypassApps: List<BankingBypassApp> = VpnServerCatalog.defaultMyanmarBankingBypassApps,
    val routingMode: com.example.model.VpnRoutingMode = com.example.model.VpnRoutingMode.FAST_ANTI_BLOCK,
    val language: AppLanguage = AppLanguage.MYANMAR,
    val errorMessage: String? = null
)

object VpnManager {
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _sessionState = MutableStateFlow(VpnSessionState())
    val sessionState: StateFlow<VpnSessionState> = _sessionState.asStateFlow()

    fun updateStatus(status: ConnectionStatus, errorMsg: String? = null) {
        _sessionState.update {
            it.copy(
                status = status,
                errorMessage = errorMsg,
                durationSeconds = if (status == ConnectionStatus.DISCONNECTED) 0L else it.durationSeconds
            )
        }
    }

    fun updateMetrics(
        durationSec: Long,
        downBps: Long,
        upBps: Long,
        totalDown: Long,
        totalUp: Long
    ) {
        _sessionState.update {
            it.copy(
                durationSeconds = durationSec,
                downloadSpeedBps = downBps,
                uploadSpeedBps = upBps,
                totalDownloadBytes = totalDown,
                totalUploadBytes = totalUp
            )
        }
    }

    fun selectServer(server: VpnServer) {
        _sessionState.update { it.copy(currentServer = server) }
    }

    fun selectProtocol(protocol: VpnProtocol) {
        _sessionState.update { it.copy(selectedProtocol = protocol) }
    }

    fun selectDns(dns: DnsOption) {
        _sessionState.update { it.copy(selectedDns = dns) }
    }

    fun selectRoutingMode(mode: com.example.model.VpnRoutingMode) {
        _sessionState.update { it.copy(routingMode = mode) }
    }

    fun setKillSwitch(enabled: Boolean) {
        _sessionState.update { it.copy(killSwitchEnabled = enabled) }
    }

    fun setBypassBanking(enabled: Boolean) {
        _sessionState.update { it.copy(bypassBankingEnabled = enabled) }
    }

    fun toggleBypassApp(packageName: String) {
        _sessionState.update { state ->
            val updated = state.bypassApps.map { app ->
                if (app.packageName == packageName) app.copy(isEnabled = !app.isEnabled)
                else app
            }
            state.copy(bypassApps = updated)
        }
    }

    fun setLanguage(language: AppLanguage) {
        _sessionState.update { it.copy(language = language) }
    }

    fun startVpn(context: Context) {
        val intent = Intent(context, ShweVpnService::class.java).apply {
            action = ShweVpnService.ACTION_CONNECT
        }
        context.startService(intent)
    }

    fun stopVpn(context: Context) {
        val intent = Intent(context, ShweVpnService::class.java).apply {
            action = ShweVpnService.ACTION_DISCONNECT
        }
        context.startService(intent)
    }
}
