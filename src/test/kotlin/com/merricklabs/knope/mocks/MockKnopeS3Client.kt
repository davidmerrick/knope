package com.merricklabs.knope.mocks

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.merricklabs.knope.external.aws.KnopeS3ClientImpl

const val LOCALSTACK_ENDPOINT = "http://localhost:4572"

open class MockKnopeS3Client : KnopeS3ClientImpl() {
    override fun buildS3(): AmazonS3 {
        val credentials = BasicAWSCredentials("XXXXX", "XXXXX")
        return AmazonS3Client.builder()
                .withPathStyleAccessEnabled(true)
                .withCredentials(AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(LOCALSTACK_ENDPOINT, config.s3.region))
                .build()
    }
}