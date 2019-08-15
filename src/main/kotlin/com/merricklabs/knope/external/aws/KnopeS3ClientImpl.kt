package com.merricklabs.knope.external.aws

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import org.koin.core.KoinComponent

open class KnopeS3ClientImpl : KoinComponent, KnopeS3Client() {
    override val s3 = buildS3()

    protected open fun buildS3(): AmazonS3 {
        return AmazonS3Client.builder().build()
    }
}