package com.scientifichamster.data.repository

import com.scientifichamster.data.model.CommentDataModel
import com.scientifichamster.data.model.PostDataModel

interface RemoteDataSource {

    suspend fun getPosts(): List<PostDataModel>

    suspend fun getPostComments(postId: Int): List<CommentDataModel>
}