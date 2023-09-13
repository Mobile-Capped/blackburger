package com.example.blackburger.network.api

import com.example.blackburger.network.resource.ResultStatut
import com.example.blackburger.network.model.response.SiteResponse
import com.example.blackburger.network.model.response.TokenApimResponse
import retrofit2.Response

interface NetworkDataSource {

    suspend fun getToken(): Response<TokenApimResponse>
    suspend fun getEligibleSites(): ResultStatut<List<SiteResponse>>

}