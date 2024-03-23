package com.backend.exp.domain.features.user.destroy

import org.springframework.http.ResponseEntity
import java.util.UUID

interface UserDestroyer {
    fun deleteById(id: UUID): ResponseEntity<Unit>
}
