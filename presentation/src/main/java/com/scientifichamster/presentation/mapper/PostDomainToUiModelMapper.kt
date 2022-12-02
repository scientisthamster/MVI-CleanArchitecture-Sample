package com.scientifichamster.presentation.mapper

import com.scientifichamster.common.Mapper
import com.scientifichamster.domain.entity.PostEntityModel
import com.scientifichamster.presentation.model.PostUiModel
import javax.inject.Inject

/**
 * Mapper class for convert [PostEntityModel] to [PostUiModel] and vice versa.
 */
class PostDomainToUiModelMapper @Inject constructor() : Mapper<PostEntityModel, PostUiModel> {

    override fun from(input: PostEntityModel?): PostUiModel {
        return PostUiModel(
            userId = input?.userId,
            id = input?.id,
            title = input?.title,
            body = input?.body
        )
    }

    override fun to(output: PostUiModel?): PostEntityModel {
        return PostEntityModel(
            userId = output?.userId,
            id = output?.id,
            title = output?.title,
            body = output?.body
        )
    }
}