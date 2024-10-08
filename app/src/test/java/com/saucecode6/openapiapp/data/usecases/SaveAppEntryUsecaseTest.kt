package com.saucecode6.openapiapp.data.usecases

import com.saucecode6.openapiapp.domain.manager.ILocalUserManager
import com.saucecode6.openapiapp.domain.usecases.SaveAppEntryUsecase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SaveAppEntryUsecaseTest {

    private val localUserManager: ILocalUserManager = mockk()
    private val saveAppEntryUsecase = SaveAppEntryUsecase(localUserManager)

    @Test
    fun `invoke should call saveAppEntity on localUserManager`() = runTest {
        coEvery { localUserManager.saveAppEntity() } returns Unit

        saveAppEntryUsecase()

        coVerify { localUserManager.saveAppEntity() }
    }
}
