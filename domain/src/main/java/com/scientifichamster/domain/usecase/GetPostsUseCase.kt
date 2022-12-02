package com.scientifichamster.domain.usecase

import com.scientifichamster.common.Resource
import com.scientifichamster.domain.entity.PostEntityModel
import com.scientifichamster.domain.qualifiers.IoDispatcher
import com.scientifichamster.domain.repository.Repository
import com.scientifichamster.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use Case class for get post list.
 */
class GetPostsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): BaseUseCase<List<PostEntityModel>, Nothing>() {

    override suspend fun buildRequest(params: Nothing?): Flow<Resource<List<PostEntityModel>>> {
        return repository.getPosts().flowOn(dispatcher)
    }
}