package com.saucecode6.openapiapp.presentation.bookmark

import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import junit.framework.TestCase.assertEquals
import org.junit.Test

class BookmarkStateTest {

    @Test
    fun defaultStateShouldHaveEmptyArticlesAndNullErrorMessage() {
        val state = BookmarkState()

        assertEquals(emptyList<Article>(), state.articles)
        assertEquals(null, state.errorMessage)
    }

    @Test
    fun stateShouldHoldProvidedArticlesAndErrorMessage() {
        val article = Article(
            author = "John Doe",
            title = "Test Article Title",
            description = "Test description",
            content = "Test content",
            publishedAt = "2023-06-16T22:24:33Z",
            source = Source(id = "bbc", name = "BBC"),
            url = "https://testarticle.com",
            urlToImage = "https://testimage.com/image.jpg"
        )
        val errorMessage = "Error loading articles"

        val state = BookmarkState(
            articles = listOf(article),
            errorMessage = errorMessage
        )


        assertEquals(listOf(article), state.articles)
        assertEquals(errorMessage, state.errorMessage)
    }
}
