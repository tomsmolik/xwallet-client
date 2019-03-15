package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinPeerInfo(
    val id: Long? = null,
    val addr: String? = null,
    val addrlocal: String? = null,
    val addrbind: String? = null,
    val services: String? = null,
    val relaytxes: Boolean? = null,
    val lastsend: Long? = null,
    val lastrecv: Long? = null,
    val bytessent: Long? = null,
    val bytesrecv: Long? = null,
    val conntime: Long? = null,
    val timeoffset: Long? = null,
    val pingtime: Double? = null,
    val minping: Double? = null,
    val version: Long? = null,
    val subver: String? = null,
    val inbound: Boolean? = null,
    val addnode: Boolean? = null,
    val startingheight: Long? = null,
    val banscore: Long? = null,
    val synced_headers: Long? = null,
    val synced_blocks: Long? = null,
    val inflight: List<*>? = null,
    val whitelisted: Boolean? = null,
    val bytessent_per_msg: BitcoinBytesPerMessage? = null,
    val bytesrecv_per_msg: BitcoinBytesPerMessage? = null
)
