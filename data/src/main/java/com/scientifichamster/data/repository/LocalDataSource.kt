package com.scientifichamster.data.repository

import com.scientifichamster.data.model.PostDataModel

interface LocalDataSource {

    suspend fun addPostItem(post: PostDataModel): Long

    suspend fun getPostItem(id: Int): PostDataModel

    suspend fun addPostItems(posts: List<PostDataModel>): List<Long>

    suspend fun getPostItems(): List<PostDataModel>

    suspend fun updatePostItem(post: PostDataModel): Int

    suspend fun deletePostItemById(id: Int): Int

    suspend fun deletePostItem(post: PostDataModel): Int

    suspend fun clearCachedPostItem(): Int
}