package com.scientifichamster.local.source

import com.scientifichamster.common.Mapper
import com.scientifichamster.data.model.PostDataModel
import com.scientifichamster.data.repository.LocalDataSource
import com.scientifichamster.local.database.PostDAO
import com.scientifichamster.local.model.PostLocalModel
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val postDAO: PostDAO,
    private val postMapper: Mapper<PostLocalModel, PostDataModel>
) : LocalDataSource {

    override suspend fun addPostItem(post: PostDataModel): Long {
        return postDAO.addPostItem(postMapper.to(post))
    }

    override suspend fun getPostItem(id: Int): PostDataModel {
        return postMapper.from(postDAO.getPostItem(id))
    }

    override suspend fun addPostItems(posts: List<PostDataModel>): List<Long> {
        return postDAO.addPostItems(postMapper.toList(posts))
    }

    override suspend fun getPostItems(): List<PostDataModel> {
        return postMapper.fromList(postDAO.getPostItems())
    }

    override suspend fun updatePostItem(post: PostDataModel): Int {
        return postDAO.updatePostItem(postMapper.to(post))
    }

    override suspend fun deletePostItemById(id: Int): Int {
        return postDAO.deletePostItemById(id)
    }

    override suspend fun deletePostItem(post: PostDataModel): Int {
        return postDAO.deletePostItem(postMapper.to(post))
    }

    override suspend fun clearCachedPostItem(): Int {
        return postDAO.clearCachedPostItems()
    }
}