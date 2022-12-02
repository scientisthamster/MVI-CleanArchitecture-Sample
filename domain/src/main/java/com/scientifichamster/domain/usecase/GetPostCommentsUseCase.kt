package com.scientifichamster.domain.usecase

import com.scientifichamster.common.Resource
import com.scientifichamster.domain.entity.CommentEntityModel
import com.scientifichamster.domain.qualifiers.IoDispatcher
import com.scientifichamster.domain.repository.Repository
import com.scientifichamster.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use Case class for get comments of post.
 */
class GetPostCommentsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<List<CommentEntityModel>, Int>() {

    override suspend fun buildRequest(params: Int?): Flow<Resource<List<CommentEntityModel>>> {
        if (params == null) {
            return flow { emit(Resource.Error(Exception("PostId can not be null"))) }.flowOn(
                dispatcher
            )
        }
        return repository.getPostComments(params).flowOn(dispatcher)
    }
}