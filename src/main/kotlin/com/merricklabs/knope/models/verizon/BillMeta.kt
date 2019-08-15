package com.merricklabs.knope.models.verizon

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BillMeta(
        @JsonProperty("total") val total: String,
        @JsonProperty("charges") val charges: String,
        @JsonProperty("balanceForward") val balanceForward: String
)