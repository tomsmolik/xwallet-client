package com.wbtcb.bitcoin.client

import com.googlecode.jsonrpc4j.JsonRpcHttpClient
import com.googlecode.jsonrpc4j.ProxyUtil
import com.wbtcb.bitcoin.security.Authorization
import java.net.URL

object BitcoinRpcClientFactory {

    fun createClient(
        user: String,
        password: String,
        host: String,
        port: String,
        https: Boolean,
        connectionTimeoutMillis: Int,
        readTimeoutMillis: Int
    ): BitcoinRpcClient {

        return ProxyUtil.createClientProxy(
            BitcoinRpcClientFactory::class.java.classLoader,
            BitcoinRpcClient::class.java,
            provideJsonRpcClient(user, password, host, port, https, connectionTimeoutMillis, readTimeoutMillis)
        )
    }

    private fun provideJsonRpcClient(
        user: String,
        password: String,
        host: String,
        port: String,
        https: Boolean,
        connectionTimeoutMillis: Int,
        readTimeoutMillis: Int
    ): JsonRpcHttpClient {

        return JsonRpcHttpClient(createUrl(user, host, port, https), createHeader(user, password))
            .apply {
                this.connectionTimeoutMillis = connectionTimeoutMillis
                this.readTimeoutMillis = readTimeoutMillis
            }
    }

    private fun createUrl(user: String, host: String, port: String, https: Boolean = false): URL =
        URL("${if (https) "https" else "http"}://$user@$host:$port")

    private fun createHeader(user: String, password: String): Map<String, String> =
        mapOf(Pair("Authorization", Authorization(user, password).getBasicCredentials()))
}
