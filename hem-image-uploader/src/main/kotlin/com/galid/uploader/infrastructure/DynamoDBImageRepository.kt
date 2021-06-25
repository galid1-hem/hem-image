package com.galid.uploader.infrastructure

import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.BatchWriteItemRequest
import com.amazonaws.services.dynamodbv2.model.PutRequest
import com.amazonaws.services.dynamodbv2.model.WriteRequest
import com.galid.uploader.domain.entity.ImageEntity
import com.galid.uploader.domain.repository.ImageRepository
import com.galid.uploader.util.AWSClient
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class DynamoDBImageRepository : ImageRepository {
    override fun save(imageEntity: ImageEntity) {
        TODO("Not yet implemented")
    }

    override fun saveAll(imageEntities: List<ImageEntity>) {
        val newItems = imageEntities.map {
            WriteRequest(PutRequest(toDynamoDBTable(it)))
        }

        AWSClient.dynamoDB
            .batchWriteItem(mapOf(TABLE_NAME to newItems))
    }

    private fun toDynamoDBTable(it: ImageEntity) = mapOf(
        "id" to AttributeValue(it.id),
        "file_path" to AttributeValue(it.filePath),
        "type" to AttributeValue(it.type.toString()),
        "service" to AttributeValue(it.service!!.value),
        "user_id" to AttributeValue(it.userId.toString()),
        "image_urls" to AttributeValue().withM(
            it.imageUrls.map { entry ->
                entry.key to AttributeValue(entry.value)
            }.toMap()
        ),
        "created_at" to AttributeValue(LocalDateTime.now().toString()),
        "updated_at" to AttributeValue(LocalDateTime.now().toString()),
    )

    companion object {
        const val TABLE_NAME = "hem-image"
    }
}