package com.backend.exp.shared

import java.text.SimpleDateFormat
import java.time.LocalDate.now
import java.time.ZoneId
import java.util.Date

fun String.addSlashIfNotPresent() = if (matches("""^\d{10}$""".toRegex())) "${substring(0, length - 4)}/${substring(length - 4)}" else this

fun String.isNotValidIdentificationNumber() = !(matches("""^\d{6}/\d{4}$""".toRegex()) && canBeConvertedToRealDate())

fun String.canBeConvertedToRealDate() =
    try {
        convertToDate().toLocalDate().isBefore(now())
    } catch (e: Exception) {
        false
    }

fun String.convertToDate(): Date {
    val dateFormat = SimpleDateFormat("yyMMdd")
    dateFormat.isLenient
    return dateFormat.parse(this.substringBeforeLast("/"))
}

fun Date.toLocalDate() =
    toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
