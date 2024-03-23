package com.backend.exp.controller

import com.backend.exp.controller.mapping.toDomain
import com.backend.exp.controller.mapping.toResourceResponseEntity
import com.backend.exp.domain.features.user.create.UserCreator
import com.backend.exp.domain.features.user.destroy.UserDestroyer
import com.backend.exp.domain.features.user.extract.UserExtractor
import com.backend.exp.shared.addSlashIfNotPresent
import com.backend.exp.shared.isNotValidIdentificationNumber
import com.openapi.exp.controller.UserApiController
import org.openapi.exp.model.UserCreationEnvelope
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class UserController(
    private val extractor: UserExtractor,
    private val creator: UserCreator,
    private val destroyer: UserDestroyer,
) : UserApiController() {
    override fun getUsersByIdentificationNumber(identificationNumber: String) =
        if (identificationNumber.addSlashIfNotPresent().isNotValidIdentificationNumber()) {
            ResponseEntity.badRequest().build()
        } else {
            extractor.getUsersByIdentificationNumber(identificationNumber.addSlashIfNotPresent()).toResourceResponseEntity()
        }

    override fun createUser(userCreationEnvelope: UserCreationEnvelope): ResponseEntity<String> {
        return creator.createUser(userCreationEnvelope.toDomain())
    }

    override fun deleteUserById(userId: UUID) = destroyer.deleteById(userId)
}
