package com.wbtcb.core.service

import com.wbtcb.core.WalletCore

abstract class BaseWalletCoreService<W : WalletCore>(val wallet: W)
