package cz.smdev.bitcoin.service

import cz.smdev.core.Wallet
import cz.smdev.core.service.BaseService
import cz.smdev.core.service.BaseWalletService

import cz.smdev.bitcoin.client.BitcoinRpcClientFactory

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
