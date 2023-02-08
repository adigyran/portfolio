package com.aya.digital.core.data.profile.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.profile.ImageUploadResult
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse

abstract class ImageUploadResultMapper :
    EntityMapper<ImageUploadResponse, ImageUploadResult>