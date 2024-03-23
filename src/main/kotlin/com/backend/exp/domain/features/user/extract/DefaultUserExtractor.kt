package com.backend.exp.domain.features.user.extract

import org.springframework.stereotype.Component

@Component
class DefaultUserExtractor(private val provider: UserExtractionProvider) : UserExtractor {
    override fun getUsersByIdentificationNumber(identificationNumber: String) =
        provider.getUsersByIdentificationNumber(identificationNumber)
}
