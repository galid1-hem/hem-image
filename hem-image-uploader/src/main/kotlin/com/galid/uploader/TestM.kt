package com.galid.uploader

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component


@Component
class TestM {
    @Value("classpath:test.txt")
    var resourceFile: Resource? = null

    fun createObject() {
        val s3: AmazonS3 = AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.AP_NORTHEAST_2)
            .build();

        val file = resourceFile?.file

        s3.putObject("hem-test-bucket", "/a", file)
    }

    fun createItems() {
        val dynamo: AmazonDynamoDB =  AmazonDynamoDBClientBuilder
            .standard()
            .withRegion(Regions.AP_NORTHEAST_2)
            .build()

        val item = mapOf<String, AttributeValue>(
            "id" to AttributeValue("1"),
            "created_at" to AttributeValue("2021"),
            "path" to AttributeValue("/app/aasd")
        )

        dynamo.putItem("hem-image", item)


    }
}


//: CommandLineRunner {
//    @Value("classpath:test.txt")
//    var resourceFile: Resource? = null
//
//    override fun run(vararg args: String?) {
//        val file = resourceFile?.file
//
//        file?.let {
//            Files.readAllLines(file?.toPath())
//                .let { println(it) }
//        }
//    }
//}