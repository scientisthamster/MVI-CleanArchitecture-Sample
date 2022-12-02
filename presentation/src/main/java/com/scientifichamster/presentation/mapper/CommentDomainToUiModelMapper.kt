package com.scientifichamster.presentation.mapper

import com.scientifichamster.common.Mapper
import com.scientifichamster.domain.entity.CommentEntityModel
import com.scientifichamster.presentation.model.CommentUiModel
import javax.inject.Inject

/**
 * Mapper class for convert [CommentEntityModel] to [CommentUiModel] and vice versa.
 */
class CommentDomainToUiModelMapper @Inject constructor(): Mapper<CommentEntityModel, CommentUiModel> {

    override fun from(input: CommentEntityModel?): CommentUiModel {
        return CommentUiModel(
            postId = input?.postId,
            id = input?.id,
            name = input?.name,
            email = input?.email,
            body = input?.body
        )
    }

    override fun to(output: CommentUiModel?): CommentEntityModel {
        return CommentEntityModel(
            postId = output?.postId,
            id = output?.id,
            name = output?.name,
            email = output?.email,
            body = output?.body
        )
    }
}