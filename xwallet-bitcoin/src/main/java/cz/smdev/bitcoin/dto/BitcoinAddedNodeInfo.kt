package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinAddedNodeInfo(
    val addednode: String? = null,
    val connected: Boolean? = null,
    val addresses: List<BitcoinNodeAddress>? = null
)
