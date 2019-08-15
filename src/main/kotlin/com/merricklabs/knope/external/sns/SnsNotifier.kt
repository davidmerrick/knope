package com.merricklabs.knope.external.sns

interface SnsNotifier {
    fun notify(message: String)
}