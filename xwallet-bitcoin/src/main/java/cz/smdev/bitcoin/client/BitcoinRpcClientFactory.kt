package cz.smdev.bitcoin.client

import com.googlecode.jsonrpc4j.JsonRpcHttpClient
import com.googlecode.jsonrpc4j.ProxyUtil

import cz.smdev.bitcoin.security.Authorization

import java.net.URL

object BitcoinRpcClientFactory {

    fun createClient(
        user: String,
        password: String,
        host: String,
        port: String,
        https: Boolean = false

    ): BitcoinRpcClient {

        return ProxyUtil.createClientProxy(
            BitcoinRpcClientFactory::class.java.classLoader,
            BitcoinRpcClient::class.java,
            provideJsonRpcClient(user, password, host, port, https)
        )
    }

    private fun provideJsonRpcClient(
        user: String,
        password: String,
        host: String,
        port: String,
        https: Boolean = false

    ): JsonRpcHttpClient {

        return JsonRpcHttpClient(
            URL("${if (https) "https" else "http"}://$user@$host:$port"),
            mapOf(Pair("Authorization", Authorization(user, password).getBasicCredentials())))
    }
}
