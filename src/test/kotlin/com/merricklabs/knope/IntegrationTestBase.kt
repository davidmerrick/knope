package com.merricklabs.knope

import com.merricklabs.knope.testutil.TestS3Client
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

open class IntegrationTestBase : KoinTest {

    private val testS3Client by inject<TestS3Client>()

    @BeforeMethod
    protected fun beforeMethod() {
        startKoin {
            modules(listOf(KnopeModule, KnopeTestModule))
        }

        initBuckets()
    }

    @AfterMethod
    protected fun afterMethod() {
        testS3Client.deleteBuckets()
        stopKoin()
    }

    private fun initBuckets() {
        testS3Client.createBuckets()

        testS3Client.uploadBill()
        testS3Client.uploadConfig()
    }
}