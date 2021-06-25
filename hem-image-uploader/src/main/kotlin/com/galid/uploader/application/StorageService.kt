package com.galid.uploader.application

import com.galid.uploader.application.dtos.ImageDto.UploadRequest

interface StorageService {
    fun upload(uploadRequest: UploadRequest)
}