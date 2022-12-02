package com.scientifichamster.remote.mapper

import com.scientifichamster.common.Mapper
import com.scientifichamster.data.model.PostDataModel
import com.scientifichamster.remote.model.PostNetworkModel
import javax.inject.Inject

/**
 * Mapper class for convert [PostNetworkModel] to [PostDataModel] and vice versa.
 */
class PostNetworkToDataModelMapper @Inject constructor() : Mapper<PostNetworkModel, PostDataModel> {
    override fun from(input: PostNetworkModel?): PostDataModel {
        return PostDataModel(
            userId = input?.userId,
            id = input?.id,
            title = input?.title,
            body = input?.body
        )
    }

    override fun to(output: PostDataModel?): PostNetworkModel {
        return PostNetworkModel(
            userId = output?.userId,
            id = output?.id,
            title = output?.title,
            body = output?.body
        )
    }
}