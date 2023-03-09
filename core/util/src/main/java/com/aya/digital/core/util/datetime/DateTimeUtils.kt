package com.aya.digital.core.util.datetime

import java.time.LocalDate

interface DateTimeUtils {
    fun parseIsoDate(date: String): LocalDate

    fun formatIsoDate(date: LocalDate):String
    fun parseYmdDate(date: String): LocalDate
    fun formatYmdDate(date: LocalDate): String
    fun formatBirthDate(date: LocalDate):String
}