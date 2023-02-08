package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.profile.ImageUploadResult
import com.aya.digital.core.data.profile.mappers.ImageUploadResultMapper
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse

internal class ImageUploadResultMapperImpl : ImageUploadResultMapper() {
    override fun mapFrom(type: ImageUploadResponse): ImageUploadResult {
        TODO("Not yet implemented")
    }
}