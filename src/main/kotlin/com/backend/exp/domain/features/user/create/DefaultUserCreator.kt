package com.backend.exp.domain.features.user.create

import org.springframework.stereotype.Component

@Component
class DefaultUserCreator(private val provider: UserCreationProvider) : UserCreator {
    override fun createUser(envelope: UserCreationEnvelope) = provider.createUser(envelope)
}
