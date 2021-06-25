package com.galid.uploader.domain.repository

import com.galid.uploader.domain.entity.ImageEntity

interface ImageRepository {
    fun save(imageEntity: ImageEntity)
    fun saveAll(imageEntities: List<ImageEntity>)
}