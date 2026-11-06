package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.VpnServerCatalog
import com.example.model.ConnectionStatus
import com.example.model.DnsOption
import com.example.model.VpnProtocol
import com.example.vpn.VpnManager
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read app_name from context verifies ITCorner`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("ITCorner", appName)
    }

    @Test
    fun `verify server catalog contains Myanmar low-latency routes`() {
        val servers = VpnServerCatalog.defaultServers
        assertTrue(servers.isNotEmpty())

        val myanmarFastLanes = servers.filter { it.isMyanmarFastLane }
        assertTrue("Must have Singapore and Thailand fast lanes", myanmarFastLanes.size >= 3)

        val sgServers = servers.filter { it.country == "Singapore" }
        assertTrue("Must include Singapore low ping servers", sgServers.isNotEmpty())
    }

    @Test
    fun `verify default Myanmar banking bypass apps are active`() {
        val bypassApps = VpnServerCatalog.defaultMyanmarBankingBypassApps
        assertTrue(bypassApps.any { it.packageName == "com.kbzbank.kpay" && it.isEnabled })
        assertTrue(bypassApps.any { it.packageName == "com.wavemoney.wavepay" && it.isEnabled })
    }

    @Test
    fun `verify VpnManager default session state`() {
        val state = VpnManager.sessionState.value
        assertEquals(ConnectionStatus.DISCONNECTED, state.status)
        assertEquals(VpnProtocol.WIREGUARD, state.selectedProtocol)
        assertEquals(DnsOption.CLOUDFLARE, state.selectedDns)
        assertEquals(com.example.model.VpnRoutingMode.FAST_ANTI_BLOCK, state.routingMode)
        assertTrue(state.bypassBankingEnabled)
    }
}
