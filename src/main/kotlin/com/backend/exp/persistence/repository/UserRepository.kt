package com.backend.exp.persistence.repository

import com.backend.exp.persistence.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface UserRepository : CrudRepository<User, UUID> {
    fun getUserByIdentificationNumber(identificationNumber: String): Optional<User>
}
