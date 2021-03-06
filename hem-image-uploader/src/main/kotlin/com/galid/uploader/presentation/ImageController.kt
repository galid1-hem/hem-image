package com.galid.uploader.presentation

import com.galid.uploader.application.ImageService
import com.galid.uploader.application.dtos.ImageDto
import com.galid.uploader.application.dtos.ImageDto.UploadResponse
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/images")
@RestController
class ImageController(
    private val imageService: ImageService
) {
    @PostMapping
    fun uploadImages(
        @ModelAttribute request: ImageDto.UploadRequest
    ): Response<List<UploadResponse>> {
        validateRequest(request)

        return Response(
            data = imageService.uploadImage(request)
        )
    }

    internal fun validateRequest(
        request: ImageDto.UploadRequest
    ) {
        if (request.files.size > UPLOAD_LIMIT_COUNT)
            throw RuntimeException("한번에 최대 업로드 가능한 이미지 수는 10장입니다.")
    }

    companion object {
        const val UPLOAD_LIMIT_COUNT = 10
    }
}