package com.saucecode6.openapiapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.saucecode6.openapiapp.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String = "${source.id}, ${source.name}"

    @TypeConverter
    fun stringToSource(string: String): Source {
        return string.split(',').let { sourceArray ->
            Source(id = sourceArray[0], name = sourceArray[1])
        }
    }
}
