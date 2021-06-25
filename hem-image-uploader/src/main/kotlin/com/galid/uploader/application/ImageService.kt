package com.galid.uploader.application

import com.galid.uploader.application.dtos.ImageDto
import com.galid.uploader.domain.entity.ImageEntity
import com.galid.uploader.domain.repository.ImageRepository
import com.galid.uploader.util.IDGenerator
import org.springframework.stereotype.Service

@Service
class ImageService(
    private val imageRepository: ImageRepository,
    private val storageService: StorageService,
    private val urlService: UrlService,
    private val idGenerator: IDGenerator<String>
) {

    fun uploadImage(
        request: ImageDto.UploadRequest
    ) {
        // Entity 생성
        val imageEntities = fromDto(request)

        // dynamoDB Item 생성
        imageRepository.saveAll(imageEntities)

        // S3에 업로드
        storageService.upload(request)
    }

    fun getImages(

    ) {

    }

    internal fun fromDto(
        request: ImageDto.UploadRequest
    ): List<ImageEntity> {
        return request.files.map {
            val filePath = it.originalFilename!!

            ImageEntity(
                id = idGenerator.getNewId(),
                filePath = filePath,
                type = request.imageType,
                service = request.serviceType,
                userId = request.userId,
                imageUrls = urlService.makeUrls(
                    filePath = filePath,
                    imageType = request.imageType,
                    serviceType = request.serviceType
                )
            )
        }
    }
}