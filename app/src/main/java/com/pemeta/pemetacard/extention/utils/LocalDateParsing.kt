package com.pemeta.pemetacard.extention.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class LocalDateParsing() {
    fun convertLocalDate(dateTimeString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.parse(dateTimeString, formatter)
        val localDateTimeCountry = DateTimeFormatter.ofPattern(
            "dd MMMM yyyy",
            Locale.forLanguageTag("ID")
           // Locale.getDefault()
        )
        return dateTime.format(localDateTimeCountry)
    }

    fun convertLocalTime(hourTimeString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.parse(hourTimeString, formatter)
        val localDateTimeCountry = DateTimeFormatter.ofPattern(
            "HH:mm",
            Locale.forLanguageTag("ID")
        )
        return dateTime.format(localDateTimeCountry)
    }

    fun convertLocalYear(yearString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.parse(yearString, formatter)
        val localDateTimeCountry = DateTimeFormatter.ofPattern(
            "yyyy",
            Locale.forLanguageTag("ID")
        )
        return dateTime.format(localDateTimeCountry)
    }
}