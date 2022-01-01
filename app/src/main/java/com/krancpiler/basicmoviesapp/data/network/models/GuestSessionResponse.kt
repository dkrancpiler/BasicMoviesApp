package com.krancpiler.basicmoviesapp.data.network.models

data class GuestSessionResponse(
    val success: Boolean,
    val guest_session_id: String,
    val expires_at: String
)
