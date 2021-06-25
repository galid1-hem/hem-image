package com.galid.uploader.util

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.stereotype.Component

class AWSClient {
    companion object {
        val s3 = AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.AP_NORTHEAST_2)
            .build()

        val dynamoDB = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion(Regions.AP_NORTHEAST_2)
            .build()
    }
}