package com.scientifichamster.data.mapper

import com.scientifichamster.common.Mapper
import com.scientifichamster.data.model.CommentDataModel
import com.scientifichamster.domain.entity.CommentEntityModel
import javax.inject.Inject

/**
 * Mapper class for convert [CommentDataModel] to [CommentEntityModel] and vice versa.
 */
class CommentsDataToDomainModelMapper @Inject constructor() : Mapper<CommentDataModel, CommentEntityModel> {

    override fun from(input: CommentDataModel?): CommentEntityModel {
        return CommentEntityModel(
            postId = input?.postId,
            id = input?.id,
            name = input?.name,
            email = input?.email,
            body = input?.body
        )
    }

    override fun to(output: CommentEntityModel?): CommentDataModel {
        return CommentDataModel(
            postId = output?.postId,
            id = output?.id,
            name = output?.name,
            email = output?.email,
            body = output?.body
        )
    }
}