package com.merricklabs.knope.models

import com.merricklabs.knope.config.PersonConfig

data class PersonTotal(
        val name: String,
        val devicePayment: Float,
        val mtn: String,
        val lineCost: Float
) {
    constructor(personSplit: PersonConfig, lineCost: Float) :
            this(personSplit.name, personSplit.devicePayment, personSplit.mtn, lineCost)

    val total by lazy { lineCost + devicePayment }
}