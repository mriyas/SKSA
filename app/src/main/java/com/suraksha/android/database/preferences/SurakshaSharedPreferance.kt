package com.suraksha.android.database.preferences

import android.content.Context

class SurakshaSharedPreferance {
    private val SHARED_PREF_NAME = "MyPrefSuraskha"
    internal var mContext: Context? = null

    constructor(context: Context?) {
        this.mContext = context
    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    fun setDoublePrefValue(key: String, value: Double?) {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        if (null == value)
            editor.putLong(key, java.lang.Double.doubleToRawLongBits(0.0))
        else
            editor.putLong(key, java.lang.Double.doubleToRawLongBits(value))
        editor.commit()
        return
    }

    fun getDouble(key: String): Double {

        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        return java.lang.Double.longBitsToDouble(pref.getLong(key, java.lang.Double.doubleToLongBits(0.0)))
    }

    /**
     * Getting boolean key value fromDeviceCursor shared preference
     *
     * @param key
     * @return
     */
    fun getBooleanPrefValue(key: String): Boolean {
        if (mContext == null) {
            return false
        }
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        return pref.getBoolean(key, false)
    }

    /**
     * Getting boolean key value fromDeviceCursor shared preference
     *
     * @param key
     * @return
     */
    fun getBooleanPrefValue2(key: String): Boolean {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        return pref.getBoolean(key, true)
    }

    /**
     * Getting boolean key value fromDeviceCursor shared preference
     *
     * @param key
     * @return
     */
    fun getLongPrefValue(key: String): Long {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        var value: Long = 0
        try {
            value = pref.getLong(key, 0)
            if (0L == value) {
                return -1
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return value
    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    fun setBooleanPrefValue(key: String, value: Boolean) {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor.putBoolean(key, value)
        editor.commit()

    }

    /**
     * Getting float key value fromDeviceCursor shared preference
     *
     * @param key
     * @return
     */
    fun getFloatPrefValue(key: String): Float {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        return pref.getFloat(key, 0.000000.toFloat())
    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    fun setFloatPrefValue(key: String, value: Float) {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor.putFloat(key, value)
        editor.commit()

    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    fun setLongPrefValue(key: String, value: Long?) {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()

        if (value != null) {
            editor.putLong(key, value)
        }
        editor.commit()

    }

    /**
     * Getting boolean key value fromDeviceCursor shared preference
     *
     * @param key
     * @return
     */
    fun getStringPrefValue(key: String): String? {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        return pref.getString(key, null)
    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    fun setStringPrefValue(key: String, value: String?) {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(key, value)
        editor.apply()

    }

    /**
     * Getting integer key value fromDeviceCursor shared preference
     *
     * @param key
     * @return
     */
    fun getIntegerPrefValue(key: String): Int {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return pref.getInt(key, -1)
    }

    /**
     * Setting a integer key value to Shared preference
     *
     * @param key
     * @param value
     */
    fun setIntegerPrefValue(key: String, value: Int?) {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()

        value?.let { editor.putInt(key, it) }
        editor.commit()
    }

    fun clear() {
        val settings = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        settings.edit().clear().commit()
    }

    interface Constants {
        companion object {
            val SERVICE="SERVICE"
            val SENSOR_BG_SCAN_UPDATE_INTERVAL = "sensor_bg_interval"
            val MY_GCM_REG_ID = "gcm_id"
            val MY_WEB_SERVICE_URL = "my_url"
            val MY_WEB_SERVICE_BUNDLE_NAME = "bundle_name"
            val MY_WEB_SERVICE_BUNDLE_ID = "bundle_id"
            val MY_PHONE_LONG_ID = "phone_long_id"
            val MY_WEB_SERVICE_TOKEN = "web_token"
            val APP_ID = "appId"
            val APP_AUTH_ENC_KEY = "authEncKey"

            val USER_LOGGED_IN = "is_logged_in"
            val IS_DEVICE_SYNCING = "is_device_syncing"
            val IS_SKIPPED_LOG_IN = "is_skipped_log_in"
            val FIRST_TIME_ALERT = "first_time_alert"
            val FIRST_TIME_TIME_SYNC_SERVER_CALLED = "first_time_server_sync"
            val LOGGED_IN_USER_NAME = "user_name"
            val LOGGED_IN_USER_DISPLAY_NAME = "user_display_name"
            /**
             * Network key to encrypt ble communication
             */
            val NETWORK_KEY = "network_key"
            val SELECTED_NETWORK_ID = "network_id1"
            val IS_FIRST_INSTALLATION = "is_first_installation"
            /**
             * Current Password for check chenge password
             */

            /**
             * Source ID to ble communication
             */
            val SOURCE_ID = "source_id"


            val IS_BRIDGE = "bridge_info"


            val LOGGED_IN_USER_ID = "user_id"
            val LOGGED_IN_USER_MESSAGE_ID = "message_id"

            val PREV_OPERATION = "prev_operation"

            val USER_EMAIL = "user_email"

            val TIME_STAMP_OPERATION_MSG_UPDATED = "ts_opn_msgs"
            val TIME_STAMP_NON_OPERATION_MSG_UPDATED = "ts_non_opn_msgs"



            val SCAN_SENSOR_DATA_IN_REMOTE = "scan_sensor_data_in_remote"
            val UPDATE_SENSOR_DATA_VIA_REMOTE = "update_sensor_data_via_remote"
            val SEND_SENSOR_DATA_TO_SERVER = "send_sensor_data_to_server"
            val SEND_SENSOR_DATA_TO_SERVER_TIME_INTERVAL = "send_sensor_data_to_server_time_interval"

            val TWELVE_BIT_SUPPORT = "twelve_bit_support"

            val SELECTED_SUB_ORG_ID = "sub_org"
            val SELECTED_FLOOR_ID = "floor_id"
            val SELECTED_BUILDING_ID = "building_id"
            val ROOT_ORG_ID = "root_org"
            val ORGANIZATION_NAME = "organization_name"

            val USER_TYPE = "user_type"

            val SELECTED_SUB_ORG_NAME = "selected_sub_org_name"

            val TIME_FROM_SERVER = "TIME_FROM_SERVER"
            val TIME_SYNC_INTERVAL = "TIME_SYNC_INTERVAL"

            val SYNC_TIME_API_CALL_TIME = "sync_api_call_time"
            val SYNC_TIME_ALERT_CALL_TIME = "sync_alert_call_time"
            val IS_SYNCED_PREFERENCE_API = "IS_SYNCED_PREFERENCE_API"

            val IS_DEVICE_IN_CONNECTABLE_MODE = "IS_DEVICE_IN_CONNECTABLE_MODE"
            val IS_CLOUD_SELECTED = "IS_CLOUD_SELECTED"
            val SYNC_TIME_ALERT_CHECK = "syncTimeAlert"

            val ON_LOCATION_ACTIVITY = "ON_LOCATION_ACTIVITY"

            val GROUP_PRIVILEGE_GRANTED = "groupPrivilegeGranted"
            val SCENE_PRIVILEGE_GRANTED = "scenePrivilegeGranted"

            val WIFI_SSID = "WIFI_SSID"
            val WIFI_PASSWORD = "WIFI_PASSWORD"
            val WIFI_AUTH_MODE = "WIFI_AUTH_MODE"
            val SYNC_LISTING_STATS = "sync_listing_status"
            val SYNC_LAST_UPDATED_TIME = "sync_last_updated_time"
            val DEFAULT_OPERATION_MULTIPLIER = "space_operation_multiplier"

            val ADV_INTERVAL = "ADV_INTERVAL"
            val TIME_TO_QUARANTINE = "TIME_TO_QUARANTINE"
            val IS_SHOW_POPUP_IN_PAIRING = "IS_SHOW_POPUP_IN_PAIRING"
            val NEW_SECURITY_CODE = "new_security_code"
            val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
            val OLD_SECURITY_CODE = "old_security_code"
            val IS_BLE_CHECKER_SHOWN = "is_ble_checker_shown"
            val USER_MANAGEMENT_PERMISSION = "user_management_permission"
        }

    }
}