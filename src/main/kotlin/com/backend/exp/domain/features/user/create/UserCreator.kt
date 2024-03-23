package com.backend.exp.domain.features.user.create

import org.springframework.http.ResponseEntity

interface UserCreator {
    fun createUser(envelope: UserCreationEnvelope): ResponseEntity<String>
}
