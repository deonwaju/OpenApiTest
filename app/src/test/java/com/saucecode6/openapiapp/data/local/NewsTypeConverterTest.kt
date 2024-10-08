package com.saucecode6.openapiapp.data.local

import com.saucecode6.openapiapp.domain.model.Source
import junit.framework.TestCase.assertEquals
import org.junit.Test

class NewsTypeConverterTest {

    private val converter = NewsTypeConverter()

    @Test
    fun `sourceToString should convert Source to String`() {
        val source = Source(id = "123", name = "Sample Source")

        val result = converter.sourceToString(source)

        assertEquals("123, Sample Source", result)
    }

    @Test
    fun `stringToSource should convert String to Source`() {
        val sourceString = "123, Sample Source"

        val result = converter.stringToSource(sourceString)

        assertEquals("123", result.id)
        assertEquals(" Sample Source", result.name)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `stringToSource should throw exception for invalid input`() {
        val invalidString = "InvalidSourceString"

        converter.stringToSource(invalidString)
    }
}
