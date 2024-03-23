package com.backend.exp.domain.features.user.destroy

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class DefaultUserDestroyer(private val provider: UserDeletingProvider) : UserDestroyer {
    override fun deleteById(id: UUID) = provider.deleteById(id)
}
