package com.backend.exp.controller.mapping

import com.backend.exp.domain.features.user.create.UserCreationEnvelope
import com.backend.exp.model.User
import com.backend.exp.shared.addSlashIfNotPresent
import org.springframework.http.ResponseEntity
import java.util.Optional
import org.openapi.exp.model.User as UserResource
import org.openapi.exp.model.UserCreationEnvelope as UserCreationEnvelopeResource

fun Optional<User>.toResourceResponseEntity() =
    if (isPresent) {
        with(get()) {
            ResponseEntity.ok(
                UserResource(
                    id = id,
                    name = name,
                    surname = surname,
                    identificationNumber = identificationNumber,
                    age = age,
                ),
            )
        }
    } else {
        ResponseEntity.notFound().build()
    }

fun UserCreationEnvelopeResource.toDomain() =
    UserCreationEnvelope(
        name = name,
        surname = surname,
        identificationNumber = identificationNumber.addSlashIfNotPresent(),
    )
