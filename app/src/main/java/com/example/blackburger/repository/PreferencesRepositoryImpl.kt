package com.example.blackburger.repository

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.blackburger.log.AppLog
import com.example.blackburger.repository.PreferencesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class PreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : PreferencesRepository {

    /**
     * Edit an Int value in DataStore transactionally in an atomic read-modify-write operation.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param value: The value to set the preference to
     */
    override suspend fun writeInt(key: String, value: Int) {
        AppLog.logDebutMethode(this, "writeInt")
        AppLog.debug(this, "writeInt(key = $key, value = $value)")
        dataStore.edit { settings ->
            val preferenceKey = intPreferencesKey(key)
            settings[preferenceKey] = value
        }
        AppLog.logFinMethode(this, "writeInt")
    }

    /**
     * Read an Int value in DataStore.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param default: The default value to return preference value is null
     * @return Int value associated to the preference
     */
    override suspend fun readInt(key: String, default: Int): Int {
        AppLog.logDebutMethode(this, "readInt")
        AppLog.debug(
            this,
            "readInt(key = $key, default = $default)"
        )
        val result = dataStore.data
            .map { preferences ->
                val preferenceKey = intPreferencesKey(key)
                preferences[preferenceKey] ?: default
            }.first()
        AppLog.logFinMethode(this, "readInt")
        return result
    }

    /**
     * Edit a Double value in DataStore transactionally in an atomic read-modify-write operation.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param value: The value to set the preference to
     */
    override suspend fun writeDouble(key: String, value: Double) {
        AppLog.logDebutMethode(this, "writeDouble")
        AppLog.debug(
            this,
            "writeDouble(key = $key, value = $value)"
        )
        dataStore.edit { settings ->
            val preferenceKey = doublePreferencesKey(key)
            settings[preferenceKey] = value
        }
        AppLog.logFinMethode(this, "writeDouble")
    }

    /**
     * Read a Double value in DataStore.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param default: The default value to return preference value is null
     * @return Double value associated to the preference
     */
    override suspend fun readDouble(key: String, default: Double): Double {
        AppLog.logDebutMethode(this, "readDouble")
        AppLog.debug(
            this,
            "readDouble(key = $key, default = $default)"
        )
        val result = dataStore.data
            .map { preferences ->
                val preferenceKey = doublePreferencesKey(key)
                preferences[preferenceKey] ?: default
            }.first()
        AppLog.logFinMethode(this, "readDouble")
        return result
    }

    /**
     * Edit a String value in DataStore transactionally in an atomic read-modify-write operation.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param value: The value to set the preference to
     */
    override suspend fun writeString(key: String, value: String) {
        AppLog.logDebutMethode(this, "writeString")
        AppLog.debug(
            this,
            "writeString(key = $key, value = $value)"
        )
        dataStore.edit { settings ->
            val preferenceKey = stringPreferencesKey(key)
            settings[preferenceKey] = value
        }
        AppLog.logFinMethode(this, "writeString")
    }


    /**
     * Read a String value in DataStore.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param default: The default value to return preference value is null
     * @return String value associated to the preference
     */
    override suspend fun readString(key: String, default: String): String {
        AppLog.logDebutMethode(this, "readString")
        AppLog.debug(
            this,
            "readString(key = $key, default = $default)"
        )
        val result = dataStore.data
            .map { preferences ->
                val preferenceKey = stringPreferencesKey(key)
                preferences[preferenceKey] ?: default
            }.first()
        AppLog.logFinMethode(this, "readString")
        return result
    }


    /**
     * Edit a Boolean value in DataStore transactionally in an atomic read-modify-write operation.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param value: The value to set the preference to
     */
    override suspend fun writeBoolean(key: String, value: Boolean) {
        AppLog.logDebutMethode(this, "writeBoolean")
        AppLog.debug(
            this,
            "writeBoolean(key = $key, value = $value)"
        )
        dataStore.edit { settings ->
            val preferenceKey = booleanPreferencesKey(key)
            settings[preferenceKey] = value
        }
        AppLog.logFinMethode(this, "writeBoolean")
    }

    /**
     * Read a Boolean value in DataStore.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param default: The default value to return preference value is null
     * @return Boolean value associated to the preference
     */
    override suspend fun readBoolean(
        key: String,
        default: Boolean
    ): Boolean {
        AppLog.logDebutMethode(this, "readBoolean")
        AppLog.debug(
            this,
            "readBoolean(key = $key, default = $default)"
        )
        val result = dataStore.data
            .map { preferences ->
                val preferenceKey = booleanPreferencesKey(key)
                preferences[preferenceKey] ?: default
            }.first()
        AppLog.logFinMethode(this, "readBoolean")
        return result
    }

    /**
     * Edit a Float value in DataStore transactionally in an atomic read-modify-write operation.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param value: The value to set the preference to
     */
    override suspend fun writeFloat(key: String, value: Float) {
        AppLog.logDebutMethode(this, "writeFloat")
        AppLog.debug(
            this,
            "writeFloat(key = $key, value = $value)"
        )
        dataStore.edit { settings ->
            val preferenceKey = floatPreferencesKey(key)
            settings[preferenceKey] = value
        }
        AppLog.logFinMethode(this, "writeFloat")
    }

    /**
     * Read a Float value in DataStore.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param default: The default value to return preference value is null
     * @return Float value associated to the preference
     */
    override suspend fun readFloat(key: String, default: Float): Float {
        AppLog.logDebutMethode(this, "readFloat")
        AppLog.debug(
            this,
            "readFloat(key = $key, default = $default)"
        )
        val result = dataStore.data
            .map { preferences ->
                val preferenceKey = floatPreferencesKey(key)
                preferences[preferenceKey] ?: default
            }.first()
        AppLog.logFinMethode(this, "readFloat")
        return result
    }

    /**
     * Edit a Long value in DataStore transactionally in an atomic read-modify-write operation.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param value: The value to set the preference to
     */
    override suspend fun writeLong(key: String, value: Long) {
        AppLog.logDebutMethode(this, "writeLong")
        AppLog.debug(
            this,
            "writeLong(key = $key, value = $value)"
        )
        dataStore.edit { settings ->
            val preferenceKey = longPreferencesKey(key)
            settings[preferenceKey] = value
        }
        AppLog.logFinMethode(this, "writeLong")
    }

    /**
     * Read a Long value in DataStore.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param default: The default value to return preference value is null
     * @return Long value associated to the preference
     */
    override suspend fun readLong(key: String, default: Long): Long {
        AppLog.logDebutMethode(this, "readLong")
        AppLog.debug(
            this,
            "readLong(key = $key, default = $default)"
        )
        val result = dataStore.data
            .map { preferences ->
                val preferenceKey = longPreferencesKey(key)
                preferences[preferenceKey] ?: default
            }.first()
        AppLog.logFinMethode(this, "readLong")
        return result
    }

    /**
     * Clear all data from DataStore.
     *
     * @param key: The key to check for
     * @param isKeyOfSecuredData: Whether or not the key is associated with a secured data
     * @param storedDataType: class of the type of stored data
     *
     * @return Returns true if this Preferences contains the specified key
     */
    override suspend fun hasKey(
        storedDataType: KClass<*>,
        key: String,
        isKeyOfSecuredData: Boolean
    ): Boolean {
        AppLog.logDebutMethode(this, "hasKey")
        AppLog.debug(
            this,
            "hasKey(key = $key)"
        )
        val theKey: Preferences.Key<*> = when (storedDataType) {
            Int::class -> intPreferencesKey(key)
            Long::class -> longPreferencesKey(key)
            Float::class -> floatPreferencesKey(key)
            Double::class -> doublePreferencesKey(key)
            Boolean::class -> booleanPreferencesKey(key)
            String::class -> stringPreferencesKey(key)
            else -> throw IllegalArgumentException("${T::class} is not an Int, a Double, Boolean, Long, Float, String")
        }
        val result = dataStore.data.map {
            it.contains(theKey)
        }.first()
        AppLog.logFinMethode(this, "hasKey")
        return result
    }

    /**
     * Clear all data from DataStore.
     *
     */
    override suspend fun clearDataStore() {
        AppLog.logDebutMethode(this, "clearDataStore")
        AppLog.debug(
            this,
            "clearDataStore()"
        )
        dataStore.edit {
            it.clear()
        }
        AppLog.logFinMethode(this, "clearDataStore")
    }

    override fun getDataStore(): DataStore<Preferences> {
        return dataStore
    }

    companion object {

        /**
         * Data store's alias to retrieve encryption/decryption key for secured process associated to data store
         */
        //TODO can we add this in config file
        private const val dataStoreKeyAlias =
            "com.laposte.surfngfacteo.core_common.repository.data-store"
    }
}