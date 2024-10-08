package com.saucecode6.openapiapp.data.usecases

import com.saucecode6.openapiapp.domain.usecases.AppEntryUsecases
import com.saucecode6.openapiapp.domain.usecases.ReadAppEntryUsecase
import com.saucecode6.openapiapp.domain.usecases.SaveAppEntryUsecase
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class AppEntryUsecasesTest {
    private val readAppEntryUsecase: ReadAppEntryUsecase = mockk()
    private val saveAppEntryUsecase: SaveAppEntryUsecase = mockk()

    @Test
    fun `AppEntryUsecases should hold the provided use cases`() {
        val appEntryUsecases = AppEntryUsecases(
            readAppEntryUsecase = readAppEntryUsecase,
            saveAppEntryUsecase = saveAppEntryUsecase
        )

        assertNotNull(appEntryUsecases.readAppEntryUsecase)
        assertNotNull(appEntryUsecases.saveAppEntryUsecase)
    }
}