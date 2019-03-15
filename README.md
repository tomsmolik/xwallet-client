# xwallet

Kotlin library providing a abstract API for interacting with multiple crypto wallet core.

## Usage:

```kotlin
 val wallet: BitcoinWallet = WalletFactory.createWallet(BitcoinWallet::class.java,
        WalletSpecification(
            user = "....",
            password = "....",
            host = "....",
            port = "...."
        ))

 wallet.walletService.isAddressValid("mtXWDB6k5yC5v7TcwKZHB89SUp85yCKshy")     
```
