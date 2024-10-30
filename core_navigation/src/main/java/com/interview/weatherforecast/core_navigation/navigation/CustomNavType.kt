package com.interview.weatherforecast.core_navigation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.interview.weatherforecast.core_navigation.args.ForecastListScreenArgs
import kotlinx.serialization.json.Json

object CustomNavType {

    val ForecastListArgs = object : NavType<ForecastListScreenArgs>(
            isNullableAllowed = true
    ) {
        override fun serializeAsValue(value: ForecastListScreenArgs): String {
            return Uri.encode(Json.encodeToString(value = value, serializer = ForecastListScreenArgs.serializer()))
        }

        override fun parseValue(value: String): ForecastListScreenArgs {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun get(bundle: Bundle, key: String): ForecastListScreenArgs? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun put(bundle: Bundle, key: String, value: ForecastListScreenArgs) {
            bundle.putString(key, Json.encodeToString(value = value, serializer = ForecastListScreenArgs.serializer()))
        }
    }
}
