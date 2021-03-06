# xwallet
[![](https://jitpack.io/v/wbtcb/xwallet-client.svg?label=Release)](https://jitpack.io/#wbtcb/xwallet-client)

Kotlin library providing a simple abstract API for interacting with multiple crypto wallet core.

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

## Gradle dependencies:

```gradle
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
    
	dependencies {
	        implementation 'com.github.wbtcb:xwallet-client:$version'
	}
```
## Maven dependencies:

```maven
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
    
	<dependency>
	    <groupId>com.github.wbtcb</groupId>
	    <artifactId>xwallet-client</artifactId>
	    <version>version</version>
	</dependency>
```
