package com.ycosilvallana.onepiece.data.local

import androidx.room.TypeConverter

class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in list) {
            stringBuilder.append(item).append(separator)
        }

        removeLastSeparator(stringBuilder)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(listAsString: String): List<String> {
        return listAsString.split(separator)
    }

    private fun removeLastSeparator(stringBuilder: StringBuilder) {
        stringBuilder.setLength(stringBuilder.length - 1)
    }
}