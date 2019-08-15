package com.merricklabs.knope.testutil

import com.amazonaws.services.s3.model.PutObjectResult
import com.google.common.io.Resources
import com.merricklabs.knope.config.KnopeConfig
import com.merricklabs.knope.external.aws.forceDeleteBucket
import com.merricklabs.knope.mocks.MockKnopeS3Client
import com.merricklabs.knope.testutil.TestData.TEST_BILL

/**
 * s3 client with extra methods for uploading bill/creating buckets
 */
class TestS3Client : MockKnopeS3Client() {

    fun uploadBill(): PutObjectResult {
        val body: String = Resources.getResource(TEST_BILL).readText()
        return s3.putObject(config.s3.bucketName, TEST_BILL, body)
    }

    fun uploadConfig(): PutObjectResult {
        val body = Resources.getResource(KnopeConfig.S3.CONFIG_FILENAME).readText()
        return s3.putObject(config.s3.configBucketName, KnopeConfig.S3.CONFIG_FILENAME, body)
    }

    fun createBuckets() {
        this.s3.createBucket(config.s3.bucketName)
        this.s3.createBucket(config.s3.configBucketName)
    }

    fun deleteBuckets() {
        this.s3.forceDeleteBucket(config.s3.bucketName)
        this.s3.forceDeleteBucket(config.s3.configBucketName)
    }
}