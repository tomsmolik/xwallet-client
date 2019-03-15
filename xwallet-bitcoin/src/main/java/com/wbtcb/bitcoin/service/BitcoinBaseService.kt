package com.wbtcb.bitcoin.service

import com.wbtcb.core.Wallet
import com.wbtcb.core.service.BaseService
import com.wbtcb.core.service.BaseWalletService
import com.wbtcb.bitcoin.client.BitcoinRpcClientFactory

open class BitcoinBaseService(wallet: Wallet) : BaseWalletService<Wallet>(wallet), BaseService {

    val bitcoinRpcClient by lazy {
        wallet.walletSpecification().let { spec ->
            BitcoinRpcClientFactory.createClient(
                spec.user,
                spec.password,
                spec.host,
                spec.port,
                spec.https
            )
        }
    }
}
