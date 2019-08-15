package com.merricklabs.knope.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

class KnopeObjectMapper : ObjectMapper() {

    init {
        this.registerModule(KotlinModule())
    }
}