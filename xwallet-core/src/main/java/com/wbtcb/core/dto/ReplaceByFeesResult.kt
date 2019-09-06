package com.wbtcb.core.dto

import java.math.BigDecimal

data class ReplaceByFeesResult(
    /**
     * The id of the new transaction
     */
    val txId: String? = null,

    /**
     * Fee of the replaced transaction
     */
    val origFee: BigDecimal? = null,

    /**
     * Fee of the new transaction
     */
    val newFee: BigDecimal? = null,

    /**
     * Errors encountered during processing (may be empty)
     */
    val errors: List<String>? = null
)
