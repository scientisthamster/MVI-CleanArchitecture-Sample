package com.scientifichamster.domain.repository

import com.scientifichamster.common.Resource
import com.scientifichamster.domain.entity.CommentEntityModel
import com.scientifichamster.domain.entity.PostEntityModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getPosts(): Flow<Resource<List<PostEntityModel>>>

    suspend fun getPostComments(postId: Int): Flow<Resource<List<CommentEntityModel>>>
}