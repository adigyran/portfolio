package com.aya.digital.core.data.base.dataprocessing

interface IServerErrorModel {
    fun getCode(): String
    fun getDetail(): String
}