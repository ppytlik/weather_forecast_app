package com.interview.weatherforecast.feature.forecast.domain.converter

import java.util.Locale
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

//Should based on kotlinx.datetime
class DateConverter(private val textLocator: TextLocator) {

    private val dateFormat = "dd.MM.yyyy"
    private val formatter = DateTimeFormatter.ofPattern(dateFormat, Locale.getDefault())

    // Should be injected, validated somehow, start day from 00:00 and end day 23:59
    private val today by lazy {
        Instant.now().atZone(ZoneId.systemDefault()).toLocalDate()
    }
    private val tomorrow by lazy {
        today.plusDays(1)
    }

    fun formatDate(instant: Instant) = when (val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()) {
        today -> textLocator.todayText
        tomorrow -> textLocator.tomorrowText
        else -> date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
    }

    fun formatFullDate(instant: Instant) = instant.atZone(ZoneId.systemDefault()).format(formatter)
}

interface TextLocator {
    val todayText: String
    val tomorrowText: String
}