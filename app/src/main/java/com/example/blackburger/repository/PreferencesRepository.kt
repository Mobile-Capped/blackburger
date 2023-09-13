package com.example.blackburger.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlin.reflect.KClass

interface PreferencesRepository {
    /**
     * Edit an Int value in DataStore transactionally in an atomic read-modify-write operation.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param value: The value to set the preference to
     */
    suspend fun writeInt(key: String, value: Int)

    /**
     * Read an Int value in DataStore.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param default: The default value to return preference value is null
     * @return Int value associated to the preference
     */
    suspend fun readInt(key: String, default: Int = 0): Int

    /**
     * Edit a Double value in DataStore transactionally in an atomic read-modify-write operation.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param value: The value to set the preference to
     */
    suspend fun writeDouble(key: String, value: Double)

    /**
     * Read a Double value in DataStore.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param default: The default value to return preference value is null
     * @return Double value associated to the preference
     */
    suspend fun readDouble(key: String, default: Double = 0.0): Double

    /**
     * Edit a String value in DataStore transactionally in an atomic read-modify-write operation.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param value: The value to set the preference to
     */
    suspend fun writeString(key: String, value: String)

    /**
     * Read a String value in DataStore.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param default: The default value to return if preference value is null
     * @return String value associated to the preference
     */
    suspend fun readString(key: String, default: String = ""): String

    /**
     * Edit a Boolean value in DataStore transactionally in an atomic read-modify-write operation.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param value: The value to set the preference to
     */
    suspend fun writeBoolean(key: String, value: Boolean)

    /**
     * Read a Boolean value in DataStore.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param default: The default value to return preference value is null
     * @return Boolean value associated to the preference
     */
    suspend fun readBoolean(key: String, default: Boolean = false): Boolean

    /**
     * Edit a Float value in DataStore transactionally in an atomic read-modify-write operation.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param value: The value to set the preference to
     */
    suspend fun writeFloat(key: String, value: Float)

    /**
     * Read a Float value in DataStore.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param default: The default value to return preference value is null
     * @return Float value associated to the preference
     */
    suspend fun readFloat(key: String, default: Float = 0.0f): Float

    /**
     * Edit a Long value in DataStore transactionally in an atomic read-modify-write operation.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param value: The value to set the preference to
     */
    suspend fun writeLong(key: String, value: Long)

    /**
     * Read a Long value in DataStore.
     *
     * @param key: The name of the preference (Preferences.Key) to edit
     * @param default: The default value to return preference value is null
     * @return Long value associated to the preference
     */
    suspend fun readLong(key: String, default: Long = 0): Long

    /**
     * Clear all data from DataStore.
     *
     * @param key: The key to check for
     * @param isKeyOfSecuredData: Whether or not the key is associated with a secured data
     * @param storedDataType: class of the type of stored data
     *
     * @return Returns true if this Preferences contains the specified key
     */
    suspend fun hasKey(
        storedDataType: KClass<*>,
        key: String,
        isKeyOfSecuredData: Boolean = false
    ): Boolean

    /**
     * Clear all data from DataStore.
     *
     */
    suspend fun clearDataStore()


    /**
     * Get the associated DataStore Object.
     *
     */
    fun getDataStore() : DataStore<Preferences>


}