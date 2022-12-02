package com.scientifichamster.local.mapper

import com.scientifichamster.common.Mapper
import com.scientifichamster.data.model.PostDataModel
import com.scientifichamster.local.model.PostLocalModel
import javax.inject.Inject

/**
 * Mapper class for convert [PostLocalModel] to [PostDataModel] and vice versa.
 */
class PostLocalToDataModel @Inject constructor() : Mapper<PostLocalModel, PostDataModel> {

    override fun from(input: PostLocalModel?): PostDataModel {
        return PostDataModel(
            userId = input?.userId,
            id = input?.id,
            title = input?.title,
            body = input?.body
        )
    }

    override fun to(output: PostDataModel?): PostLocalModel {
        return PostLocalModel(
            id = output?.id,
            userId = output?.userId,
            title = output?.title,
            body = output?.body
        )
    }
}