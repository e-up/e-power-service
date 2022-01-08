package me.iamee.power_service.compatibility

import android.content.ComponentName
import android.content.Context
import android.content.Intent

interface Compatibility {

    fun open(context: Context, target: String, activity: String) {
        val intent = Intent()
        intent.component = ComponentName(target, activity)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun tryOpen(context: Context, target: String, activity: String): Boolean {
        return try {
            open(context = context, target = target, activity = activity)
            true
        } catch (ex: Exception) {
            false
        }
    }

    fun open(context: Context, target: String) {
        val intent = context.packageManager.getLaunchIntentForPackage(target)
        intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun tryOpen(context: Context, target: String): Boolean {
        return try {
            open(context = context, target = target)
            true
        } catch (ex: Exception) {
            false
        }
    }

    fun request(context: Context) {}

    fun supported(context: Context): Boolean

}