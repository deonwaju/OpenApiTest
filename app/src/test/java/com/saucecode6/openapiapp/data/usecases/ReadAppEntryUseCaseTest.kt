package com.saucecode6.openapiapp.data.usecases

import com.saucecode6.openapiapp.domain.manager.ILocalUserManager
import com.saucecode6.openapiapp.domain.usecases.ReadAppEntryUsecase
import com.saucecode6.openapiapp.domain.usecases.SaveAppEntryUsecase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ReadAppEntryUseCaseTest {

    private val localUserManager: ILocalUserManager = mockk()
    private val readAppEntryUsecase = ReadAppEntryUsecase(localUserManager)

    @Test
    fun `invoke should call readAppEntry on localUserManager`() = runTest {
        coEvery { localUserManager.readAppEntry() } returns flowOf(false)

        readAppEntryUsecase()

        coVerify(exactly = 1) { localUserManager.readAppEntry() }
    }
}