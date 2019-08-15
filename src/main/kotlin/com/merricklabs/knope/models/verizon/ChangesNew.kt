package com.merricklabs.knope.models.verizon

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ChangesNew(
        @JsonProperty("PreviousBill") val previousBill: BillBody,
        @JsonProperty("CurrentBill") val currentBill: BillBody,
        @JsonProperty("NextMonthBill") val nextMonthBill: BillBody
)