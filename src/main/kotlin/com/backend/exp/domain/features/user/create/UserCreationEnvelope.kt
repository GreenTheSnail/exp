package com.backend.exp.domain.features.user.create

data class UserCreationEnvelope(
    val name: String,
    val surname: String,
    val identificationNumber: String,
)
