package com.scientifichamster.remote.source

import com.scientifichamster.common.Mapper
import com.scientifichamster.data.model.CommentDataModel
import com.scientifichamster.data.model.PostDataModel
import com.scientifichamster.data.repository.RemoteDataSource
import com.scientifichamster.remote.api.ApiService
import com.scientifichamster.remote.model.CommentNetworkModel
import com.scientifichamster.remote.model.PostNetworkModel
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val postMapper: Mapper<PostNetworkModel, PostDataModel>,
    private val commentMapper: Mapper<CommentNetworkModel, CommentDataModel>
) : RemoteDataSource {

    override suspend fun getPosts(): List<PostDataModel> {
        return postMapper.fromList(apiService.getPosts())
    }

    override suspend fun getPostComments(postId: Int): List<CommentDataModel> {
        return commentMapper.fromList(apiService.getPostComments(postId))
    }
}