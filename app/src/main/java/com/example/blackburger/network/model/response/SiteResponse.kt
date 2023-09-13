package com.example.blackburger.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SiteResponse(
    @SerialName("code_regate")
    val codeRegate: String,
    @SerialName("libelle_site")
    val libelleSite: String,
    @SerialName("type_site")
    val typeSite: String,
    @SerialName("code_metier")
    val codeMetier: String,
    @SerialName("id_partenaire")
    val idPartenaire: String,
    @SerialName("flag_actif")
    val flagActif: Boolean,
    @SerialName("sitesRattaches")
    val sitesRattaches: List<SiteRattacheResponse>? = null
)

@Serializable
data class SiteRattacheResponse(
    @SerialName("code_regate")
    val codeRegate: String,
    @SerialName("libelle_site")
    val libelleSite: String,
    @SerialName("flag_actif")
    val flagActif: Boolean,
)
