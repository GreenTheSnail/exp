package com.backend.exp.domain.features.user.extract

import com.backend.exp.model.User
import java.util.Optional

interface UserExtractor {
    fun getUsersByIdentificationNumber(identificationNumber: String): Optional<User>
}
