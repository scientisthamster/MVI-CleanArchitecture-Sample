package com.scientifichamster.data.mapper

import com.scientifichamster.common.Mapper
import com.scientifichamster.data.model.PostDataModel
import com.scientifichamster.domain.entity.PostEntityModel
import javax.inject.Inject

/**
 * Mapper class for convert [PostDataModel] to [PostEntityModel] and vice versa.
 */
class PostDataToDomainModelMapper @Inject constructor(): Mapper<PostDataModel, PostEntityModel> {

    override fun from(input: PostDataModel?): PostEntityModel {
        return PostEntityModel(
            userId = input?.userId,
            id = input?.id,
            title = input?.title,
            body = input?.body
        )
    }

    override fun to(output: PostEntityModel?): PostDataModel {
        return PostDataModel(
            userId = output?.userId,
            id = output?.id,
            title = output?.title,
            body = output?.body
        )
    }
}