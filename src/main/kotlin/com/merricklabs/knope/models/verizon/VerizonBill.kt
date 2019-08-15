package com.merricklabs.knope.models.verizon

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Model of a Verizon bill
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class VerizonBill(
        @JsonProperty("data") val data: BillData
) {
    @JsonIgnore
    val currentBill = this.data.changes_new.currentBill

    fun getSurcharges() = currentBill
            .items
            .first { it.label == Labels.SURCHARGES }
            .value

    fun getTaxes() = currentBill
            .items
            .first { it.label == Labels.TAXES }
            .value

    fun getAccountCharges() = this.data
            .accountSummaryDetails
            .first { it.header == Headers.ACCOUNT_CHARGES }
            .value

    fun getOneTimeFees() = currentBill
            .items
            .first { it.label == Labels.ONE_TIME_FEES }
            .value

    fun getChargesForMtn(mtn: String) = this.data
            .lineLevelDetails
            .first { detail -> detail.mtn == mtn }
            .items
            .first { it.header == Headers.MONTHLY_CHARGES }
            .value
}