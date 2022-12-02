package com.scientifichamster.remote.api

import com.scientifichamster.remote.model.CommentNetworkModel
import com.scientifichamster.remote.model.PostNetworkModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * The main service that handle all endpoint process.
 */
interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<PostNetworkModel>

    @GET("posts/{postId}/comments")
    suspend fun getPostComments(@Path("postId") postId: Int): List<CommentNetworkModel>
}