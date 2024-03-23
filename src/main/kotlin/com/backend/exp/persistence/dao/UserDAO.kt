package com.backend.exp.persistence.dao

import com.backend.exp.domain.features.user.create.UserCreationEnvelope
import com.backend.exp.domain.features.user.create.UserCreationProvider
import com.backend.exp.domain.features.user.destroy.UserDeletingProvider
import com.backend.exp.domain.features.user.extract.UserExtractionProvider
import com.backend.exp.model.User
import com.backend.exp.persistence.model.toDomain
import com.backend.exp.persistence.model.toEntity
import com.backend.exp.persistence.repository.UserRepository
import com.backend.exp.shared.NAME_IS_BLANK
import com.backend.exp.shared.NON_VALID_IDENTIFICATION_NUMBER
import com.backend.exp.shared.SURNAME_IS_BLANK
import com.backend.exp.shared.USER_WITH_SAME_IDENTIFICATION_NUMBER_EXISTS
import com.backend.exp.shared.isNotValidIdentificationNumber
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.Optional
import java.util.UUID

@Transactional
@Component
class UserDAO(
    private val userRepository: UserRepository,
) : UserExtractionProvider,
    UserCreationProvider,
    UserDeletingProvider {
    @Transactional(readOnly = true)
    override fun getUsersByIdentificationNumber(identificationNumber: String): Optional<User> =
        userRepository.getUserByIdentificationNumber(identificationNumber).map { it.toDomain() }

    override fun createUser(envelope: UserCreationEnvelope): ResponseEntity<String> =
        when {
            envelope.name.isBlank() -> ResponseEntity.badRequest().body(NAME_IS_BLANK)
            envelope.surname.isBlank() -> ResponseEntity.badRequest().body(SURNAME_IS_BLANK)
            envelope.identificationNumber.isNotValidIdentificationNumber() ->
                ResponseEntity.badRequest().body(
                    NON_VALID_IDENTIFICATION_NUMBER,
                )
            userRepository.getUserByIdentificationNumber(
                envelope.identificationNumber,
            ).isPresent -> ResponseEntity.badRequest().body(USER_WITH_SAME_IDENTIFICATION_NUMBER_EXISTS)
            else -> ResponseEntity.ok(userRepository.save(envelope.toEntity()).id.toString())
        }

    override fun deleteById(id: UUID) =
        if (!userRepository.findById(id).isPresent) ResponseEntity.notFound().build() else ResponseEntity.ok(userRepository.deleteById(id))
}
