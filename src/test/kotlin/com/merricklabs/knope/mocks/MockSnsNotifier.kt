package com.merricklabs.knope.external.sns

import org.koin.core.KoinComponent

class MockSnsNotifier : SnsNotifier, KoinComponent {
    override fun notify(message: String) {
        // Do nothing
    }
}