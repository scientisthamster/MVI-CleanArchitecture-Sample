package com.scientifichamster.domain.usecase.core

import androidx.annotation.Nullable
import com.scientifichamster.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Base class for all usecases.
 */
abstract class BaseUseCase<Model, Params> {

    abstract suspend fun buildRequest(@Nullable params: Params?): Flow<Resource<Model>>

    suspend fun execute(@Nullable params: Params?): Flow<Resource<Model>> {
        return try {
            buildRequest(params)
        } catch (exception: Exception) {
            flow { emit(Resource.Error(exception)) }
        }
    }
}