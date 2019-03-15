package cz.smdev.core.service

import cz.smdev.core.Wallet

abstract class BaseWalletService<W : Wallet>(val wallet: W)
