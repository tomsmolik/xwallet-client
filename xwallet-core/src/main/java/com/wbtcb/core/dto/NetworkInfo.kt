package com.wbtcb.core.dto

data class NetworkInfo(
    val version: Long,
    val subversion: String?,
    val protocolVersion: Long?,
    val connections: Int
)
