package com.merricklabs.knope.models.verizon

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BillItem(
        @JsonProperty("header") val header: String?,
        @JsonProperty("label") val label: String?,
        @JsonProperty("value") private val _value: String?,
        @JsonProperty("items") val items: List<BillItem>?
) {
    val value: Float
        get() {
            return _value!!.replace("$", "").toFloat()
        }
}