package com.aya.digital.core.networkbase.server

interface IServerError {
    fun getCode(): String
    fun getDetail(): String
}