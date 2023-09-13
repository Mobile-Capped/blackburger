package com.example.blackburger.network.api

import com.example.blackburger.network.const.TOKEN_ENDPOINT
import com.laposte.surfngfacteo.core_network.const.*
import com.example.blackburger.network.model.response.SiteResponse
import com.example.blackburger.network.model.response.TokenApimResponse
import com.laposte.surfngfacteo.core_network.usecase.EndPoint
import com.laposte.surfngfacteo.core_network.usecase.Sites
import com.laposte.surfngfacteo.core_network.usecase.Token
import retrofit2.Response
import retrofit2.http.*

interface NetworkApi {

    /**
     * get token ws
     */
    @POST(TOKEN_ENDPOINT)
    suspend fun getToken(
        @Tag endPoint: EndPoint = Token,
        @Query("grant_type") request: String = "client_credentials"
    ): Response<TokenApimResponse>

    /**
     * get sites list
     */
    @GET("/")
    suspend fun getEligibleSites(@Tag endPoint: EndPoint = Sites): Response<List<SiteResponse>>

}