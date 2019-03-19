package com.wbtcb.bitcoin.exception

import com.wbtcb.core.exeption.WalletCoreException

class BitcoinWalletException(code: Int, cause: Throwable) : WalletCoreException(code, cause)
