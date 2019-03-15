package cz.smdev.bitcoin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitcoinUtxoSet(
    val height: Int? = null,
    val bestblock: String? = null,
    val transactions: Int? = null,
    val txouts: Int? = null,
    val bogosize: Long? = null,
    val hash_serialized_2: String? = null,
    val disk_size: Long? = null,
    val total_amount: BigDecimal? = null
)
