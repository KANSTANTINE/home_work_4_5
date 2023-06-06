package kg.geeks.taskapp5.data.local.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(context: Context) {

    private val pref = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

    //ON BOARDING
    fun isBoardingSeen(): Boolean {
        return pref.getBoolean(BOARD_SEEN_KEY, false)
    }

    fun boardingIsSeen() {
        pref.edit().putBoolean(BOARD_SEEN_KEY, true).apply()
    }

    //PROFILE USER NAME
    fun saveUserName(name: String?) {
        pref.edit().putString(USER_NAME_PROFILE, name).apply()
    }

    fun getUserName(): String? {
        return pref.getString(USER_NAME_PROFILE, "")
    }

    //PROFILE USER IMAGE
    fun saveUserImage(uri: String?){
        pref.edit().putString(USER_IMAGE_PROFILE, uri).apply()
    }

    fun getUserImage(): String?{
        return pref.getString(USER_IMAGE_PROFILE, "")
    }

    companion object {
        const val SHARED_PREF_NAME = "task_app"
        const val BOARD_SEEN_KEY = "isBoardSeen"
        const val USER_NAME_PROFILE = "user_name.profile"
        const val USER_IMAGE_PROFILE = "user_image.profile"
    }
}