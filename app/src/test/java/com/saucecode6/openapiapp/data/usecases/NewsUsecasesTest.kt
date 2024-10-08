package com.saucecode6.openapiapp.data.usecases

import com.saucecode6.openapiapp.domain.usecases.AllLocalArticlesUseCase
import com.saucecode6.openapiapp.domain.usecases.DeleteArticleUseCase
import com.saucecode6.openapiapp.domain.usecases.GetLocalArticleByUrlUseCase
import com.saucecode6.openapiapp.domain.usecases.GetNewsUseCase
import com.saucecode6.openapiapp.domain.usecases.NewsUsecases
import com.saucecode6.openapiapp.domain.usecases.SearchNewsUseCase
import com.saucecode6.openapiapp.domain.usecases.UpsertArticleUseCase
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class NewsUsecasesTest {

    private val getNewsUseCase: GetNewsUseCase = mockk()
    private val searchNewsUseCase: SearchNewsUseCase = mockk()
    private val upsertArticleUseCase: UpsertArticleUseCase = mockk()
    private val deleteArticleUseCase: DeleteArticleUseCase = mockk()
    private val selectArticles: AllLocalArticlesUseCase = mockk()
    private val getLocalArticleByUrlUseCase: GetLocalArticleByUrlUseCase = mockk()

    @Test
    fun `NewsUsecases should hold the provided use cases`() {
        val newsUsecases = NewsUsecases(
            getNewsUseCase = getNewsUseCase,
            searchNewsUseCase = searchNewsUseCase,
            upsertArticleUseCase = upsertArticleUseCase,
            deleteArticleUseCase = deleteArticleUseCase,
            selectArticles = selectArticles,
            getLocalArticleByUrlUseCase = getLocalArticleByUrlUseCase
        )

        assertNotNull(newsUsecases.getNewsUseCase)
        assertNotNull(newsUsecases.searchNewsUseCase)
        assertNotNull(newsUsecases.upsertArticleUseCase)
        assertNotNull(newsUsecases.deleteArticleUseCase)
        assertNotNull(newsUsecases.selectArticles)
        assertNotNull(newsUsecases.getLocalArticleByUrlUseCase)
    }
}
