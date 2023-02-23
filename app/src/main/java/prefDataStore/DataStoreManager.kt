import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DataStoreManager(private val context: Context) {

    companion object {
        private const val USER_DATASTORE = "user_datastore"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)

        val EMAIL = stringPreferencesKey("EMAIL")
        val UID = stringPreferencesKey("UID")
    }

    suspend fun saveToDataStore(userDetail: UserDetail) {
        context.dataStore.edit {
            it[EMAIL] = userDetail.emailAddress
            it[UID] = userDetail.uid
        }
    }

    val email: Flow<String> = context.dataStore.data.map {
        it[EMAIL] ?: ""
    }

    val uid: Flow<String> = context.dataStore.data.map {
        it[UID] ?: ""
    }

    fun getFromDataStore() = context.dataStore.data.map {
        it[EMAIL] ?: ""
        it[UID] ?: ""
    }


    suspend fun clearDataStore() = context.dataStore.edit {
        it.clear()
    }

}

data class UserDetail(
    val emailAddress: String = "",
    val uid: String = "",
)
