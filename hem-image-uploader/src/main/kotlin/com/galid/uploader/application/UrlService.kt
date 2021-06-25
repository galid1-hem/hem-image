package com.galid.uploader.application

import com.galid.uploader.domain.entity.ImageEntity
import com.galid.uploader.domain.entity.ImageEntity.Size
import com.galid.uploader.domain.entity.ImageEntity.Size.*

interface UrlService {
    fun makeUrls(
        sizes: List<Size> = listOf(SMALL, MEDIUM, LARGE),
        filePath: String,
        serviceType: ImageEntity.ServiceType,
        imageType: ImageEntity.ImageType,
    ): Map<String, String>
}