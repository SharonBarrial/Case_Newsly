package com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.communication

import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.model.News

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */

class UserResponse(
    private var publishedAt: String,
    private var author: String,
    private var title: String,
    private var description: String,
    private var url: UserLinkApiResponse,
    private var urlToImage: UserPictureApiResponse,
) {
    fun toNews(): News {
        return News(
            publishedAt = publishedAt,
            author = author,
            title = title,
            description = description,
            url = url.large,
            urlToImage = urlToImage.large
        )
    }
}

data class UserPictureApiResponse (
    var large: String,
    var medium: String,
    var thumbnail: String
)

data class UserLinkApiResponse (
    var large: String,
    var medium: String,
    var thumbnail: String
)
