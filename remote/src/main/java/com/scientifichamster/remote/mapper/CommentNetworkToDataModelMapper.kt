package com.scientifichamster.remote.mapper

import com.scientifichamster.common.Mapper
import com.scientifichamster.data.model.CommentDataModel
import com.scientifichamster.remote.model.CommentNetworkModel
import javax.inject.Inject

/**
 * Mapper class for convert [CommentNetworkModel] to [CommentDataModel] and vice versa.
 */
class CommentNetworkToDataModelMapper @Inject constructor() :
    Mapper<CommentNetworkModel, CommentDataModel> {
    override fun from(input: CommentNetworkModel?): CommentDataModel {
        return CommentDataModel(
            postId = input?.postId,
            id = input?.id,
            name = input?.name,
            email = input?.email,
            body = input?.body
        )
    }

    override fun to(output: CommentDataModel?): CommentNetworkModel {
        return CommentNetworkModel(
            postId = output?.postId,
            id = output?.id,
            name = output?.name,
            email = output?.email,
            body = output?.body
        )
    }
}