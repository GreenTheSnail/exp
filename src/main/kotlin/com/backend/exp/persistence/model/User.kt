package com.backend.exp.persistence.model

import com.backend.exp.domain.features.user.create.UserCreationEnvelope
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID
import com.backend.exp.model.User as UserDomainObject

@Entity(name = "\"user\"")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,
    val name: String,
    val surname: String,
    @Column(unique = true)
    val identificationNumber: String,
)

fun User.toDomain() =
    UserDomainObject(
        id = id,
        name = name,
        surname = surname,
        identificationNumber = identificationNumber,
    )

fun UserCreationEnvelope.toEntity() =
    User(
        id = UUID.randomUUID(),
        name = name,
        surname = surname,
        identificationNumber = identificationNumber,
    )
