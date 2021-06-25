package com.galid.uploader.infrastructure

import com.amazonaws.services.s3.model.ObjectMetadata
import com.galid.uploader.application.StorageService
import com.galid.uploader.application.dtos.ImageDto
import com.galid.uploader.application.dtos.ImageDto.*
import com.galid.uploader.domain.entity.ImageEntity.ServiceType
import com.galid.uploader.util.AWSClient
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class S3StorageService: StorageService {
    override fun upload(uploadRequest: UploadRequest) {
        uploadRequest.files.forEach {
            val key = getKey(serviceType = uploadRequest.serviceType, filePath = it.originalFilename!!)
            val metaData = getMetaData(it)

            AWSClient.s3.putObject(
                BUCKET_NAME,
                key,
                it.inputStream,
                metaData
            )
        }

    }

    private fun getMetaData(it: MultipartFile): ObjectMetadata {
        val metaData = ObjectMetadata()
        metaData.contentType = it.contentType
        metaData.contentLength = it.size
        return metaData
    }

    internal fun getKey(
        serviceType: ServiceType,
        filePath: String
    ): String {
        return "${serviceType.value}/$filePath"
    }

    companion object {
        const val BUCKET_NAME = "hem-image"
    }
}