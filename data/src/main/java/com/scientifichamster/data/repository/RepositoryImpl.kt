package com.scientifichamster.data.repository

import com.scientifichamster.common.Mapper
import com.scientifichamster.common.Resource
import com.scientifichamster.data.mapper.CommentsDataToDomainModelMapper
import com.scientifichamster.data.mapper.PostDataToDomainModelMapper
import com.scientifichamster.data.model.CommentDataModel
import com.scientifichamster.data.model.PostDataModel
import com.scientifichamster.domain.entity.CommentEntityModel
import com.scientifichamster.domain.entity.PostEntityModel
import com.scientifichamster.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation class of [Repository]
 */
class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val commentsMapper: Mapper<CommentDataModel, CommentEntityModel>,
    private val postMapper: Mapper<PostDataModel, PostEntityModel>,
) : Repository {

    override suspend fun getPosts(): Flow<Resource<List<PostEntityModel>>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getPosts()
                // Save to local storage
                localDataSource.addPostItems(data)
                // Emit data
                emit(Resource.Success(postMapper.fromList(data)))
            } catch (exception: Exception) {
                // If remote request fails
                try {
                    // Get data from local storage
                    val local = localDataSource.getPostItems()
                    // Emit data
                    emit(Resource.Success(postMapper.fromList(local)))
                } catch (exception2: Exception) {
                    // Emit error
                    emit(Resource.Error(exception2))
                }
            }
        }
    }

    override suspend fun getPostComments(postId: Int): Flow<Resource<List<CommentEntityModel>>> {
        return flow {
            try {
                // Get data from remote source
                val data = remoteDataSource.getPostComments(postId)
                // Emit data
                emit(Resource.Success(commentsMapper.fromList(data)))
            } catch (exception: Exception) {
                // Emit error
                emit(Resource.Error(exception))
            }
        }
    }
}