package com.aya.digital.core.util.mappers.base

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <From> the cached model input type
 * @param <From> the remote model input type
 * @param <To> the model return type
 */
interface EntityMapperWithParam<From, To, Param> {
    fun mapFrom(type: From, param: Param): To

    fun mapFromList(type: List<From>, param: Param): List<To> = type.map { mapFrom(it, param) }
}