package com.backend.exp.model

import com.backend.exp.shared.convertToDate
import com.backend.exp.shared.toLocalDate
import java.time.LocalDate.now
import java.time.Period
import java.util.UUID

class User(
    val id: UUID,
    val name: String,
    val surname: String,
    val identificationNumber: String,
) {
    val age: Int
        get() = Period.between(identificationNumber.convertToDate().toLocalDate(), now()).years
}
