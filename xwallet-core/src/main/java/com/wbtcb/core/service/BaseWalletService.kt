package com.wbtcb.core.service

import com.wbtcb.core.Wallet

abstract class BaseWalletService<W : Wallet>(val wallet: W)
