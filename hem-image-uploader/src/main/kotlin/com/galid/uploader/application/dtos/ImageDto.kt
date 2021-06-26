package com.galid.uploader.application.dtos

import com.galid.uploader.domain.entity.ImageEntity.ServiceType
import com.galid.uploader.domain.entity.ImageEntity.ImageType
import org.springframework.web.multipart.MultipartFile

class ImageDto {
    data class UploadRequest(
        val files: List<MultipartFile>,
        val userId: Long,
        val imageType: ImageType,
        val serviceType: ServiceType,
    )

    data class UploadResponse(
        val id: String,
        val thumbnailUrl: String
    )

    data class GetRequest(
        val mediaIds: List<String>
    )
}