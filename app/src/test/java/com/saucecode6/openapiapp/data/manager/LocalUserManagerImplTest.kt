import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.saucecode6.openapiapp.data.manager.LocalUserManagerImpl
import com.saucecode6.openapiapp.util.AppPreferences
import com.saucecode6.openapiapp.util.AppPreferences.dataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalUserManagerImplTest {

    private lateinit var context: Context
    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var localUserManager: LocalUserManagerImpl

    @Before
    fun setup() {
        // Use ApplicationProvider.getApplicationContext to get a mock context for testing
        context = ApplicationProvider.getApplicationContext()

        // Initialize DataStore and LocalUserManagerImpl
        dataStore = context.dataStore
        localUserManager = LocalUserManagerImpl(context)
    }

    @Test
    fun `saveAppEntity should save true to APP_ENTRY`() = runTest {
        // When: Saving APP_ENTRY as true
        localUserManager.saveAppEntity()

        // Then: Assert that APP_ENTRY is saved as true
        val preferences = dataStore.data.first()
        val isAppEntrySaved = preferences[AppPreferences.PreferencesKeys.APP_ENTRY] ?: false
        assertEquals(true, isAppEntrySaved)
    }

    @Test
    fun `readAppEntry should return true when APP_ENTRY is true`() = runTest {
        // Given: Save APP_ENTRY as true
        dataStore.edit { settings ->
            settings[AppPreferences.PreferencesKeys.APP_ENTRY] = true
        }

        // When: Reading APP_ENTRY
        val result = localUserManager.readAppEntry().first()

        // Then: Assert that the result is true
        assertEquals(true, result)
    }

    @Test
    fun `readAppEntry should return false when APP_ENTRY is not set`() = runTest {
        // When: Reading APP_ENTRY without setting it first
        val result = localUserManager.readAppEntry().first()

        // Then: Assert that the default value is false
        assertEquals(false, result)
    }
}
