package com.example.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.VpnService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.VpnServerCatalog
import com.example.model.AppLanguage
import com.example.model.ConnectionStatus
import com.example.model.DnsOption
import com.example.model.VpnProtocol
import com.example.model.VpnServer
import com.example.network.NetworkProber
import com.example.vpn.VpnManager
import com.example.vpn.VpnSessionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class MainTab {
    HOME,
    SERVERS,
    SPEED_TEST,
    SETTINGS
}

data class VpnUiState(
    val session: VpnSessionState = VpnSessionState(),
    val currentTab: MainTab = MainTab.HOME,
    val serverList: List<VpnServer> = VpnServerCatalog.defaultServers,
    val isPingingServers: Boolean = false,
    val serverSearchQuery: String = "",
    val selectedServerFilter: String = "ALL", // "RECOMMENDED", "MYANMAR_LANE", "GAMING", "ALL"
    val showServerBottomSheet: Boolean = false
)

class VpnViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VpnUiState())
    val uiState: StateFlow<VpnUiState> = combine(
        _uiState,
        VpnManager.sessionState
    ) { currentUi, session ->
        currentUi.copy(session = session)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), VpnUiState())

    val speedTestState = NetworkProber.speedTestState

    init {
        // Initial ping check
        refreshServerPings()
    }

    fun setTab(tab: MainTab) {
        _uiState.update { it.copy(currentTab = tab) }
    }

    fun setSearchQuery(query: String) {
        _uiState.update { it.copy(serverSearchQuery = query) }
    }

    fun setServerFilter(filter: String) {
        _uiState.update { it.copy(selectedServerFilter = filter) }
    }

    fun setServerSheetVisible(visible: Boolean) {
        _uiState.update { it.copy(showServerBottomSheet = visible) }
    }

    fun selectServer(server: VpnServer) {
        VpnManager.selectServer(server)
        _uiState.update { it.copy(showServerBottomSheet = false) }
    }

    fun selectProtocol(protocol: VpnProtocol) {
        VpnManager.selectProtocol(protocol)
    }

    fun selectDns(dns: DnsOption) {
        VpnManager.selectDns(dns)
    }

    fun selectRoutingMode(mode: com.example.model.VpnRoutingMode) {
        VpnManager.selectRoutingMode(mode)
    }

    fun setKillSwitch(enabled: Boolean) {
        VpnManager.setKillSwitch(enabled)
    }

    fun setBypassBanking(enabled: Boolean) {
        VpnManager.setBypassBanking(enabled)
    }

    fun toggleBypassApp(packageName: String) {
        VpnManager.toggleBypassApp(packageName)
    }

    fun toggleLanguage() {
        val current = _uiState.value.session.language
        val next = if (current == AppLanguage.MYANMAR) AppLanguage.ENGLISH else AppLanguage.MYANMAR
        VpnManager.setLanguage(next)
    }

    fun autoSelectFastestServer() {
        val fastest = _uiState.value.serverList.minByOrNull { it.pingMs }
        fastest?.let { selectServer(it) }
    }

    fun refreshServerPings() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isPingingServers = true) }
            val currentServers = _uiState.value.serverList.map { server ->
                val newPing = NetworkProber.probeHostLatency(server.host, server.pingMs)
                server.copy(pingMs = newPing)
            }
            _uiState.update {
                it.copy(
                    serverList = currentServers,
                    isPingingServers = false
                )
            }
        }
    }

    fun handleConnectToggle(
        context: Context,
        prepareVpnLauncher: () -> Unit
    ) {
        val currentStatus = _uiState.value.session.status
        if (currentStatus == ConnectionStatus.CONNECTED || currentStatus == ConnectionStatus.CONNECTING) {
            VpnManager.stopVpn(context)
        } else {
            // Check if VpnService permission is needed
            val intent = VpnService.prepare(context)
            if (intent != null) {
                prepareVpnLauncher()
            } else {
                VpnManager.startVpn(context)
            }
        }
    }

    fun onVpnPermissionGranted(context: Context) {
        VpnManager.startVpn(context)
    }

    fun runSpeedTest() {
        viewModelScope.launch {
            NetworkProber.runSpeedTest()
        }
    }

    fun resetSpeedTest() {
        NetworkProber.resetSpeedTest()
    }
}
