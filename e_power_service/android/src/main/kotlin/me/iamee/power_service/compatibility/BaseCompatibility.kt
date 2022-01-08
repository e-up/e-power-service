package me.iamee.power_service.compatibility

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

class BaseCompatibility : Compatibility {

    override fun request(context: Context) {
        val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
        intent.data = Uri.parse("package:${context.packageName}")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    override fun supported(context: Context): Boolean {
        return true
    }

}