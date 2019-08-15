package com.merricklabs.knope.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule

class ConfigObjectMapper : ObjectMapper(YAMLFactory()) {

    init {
        this.registerModule(KotlinModule())
        this.findAndRegisterModules()
    }
}