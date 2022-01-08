package me.iamee.power_service.compatibility

import android.content.Context
import android.os.Build
import java.util.*

class XiaomiCompatibility : Compatibility {

    private val target = "com.miui.securitycenter"

    private val activity = "com.miui.permcenter.autostart.AutoStartManagementActivity"

    override fun supported(context: Context): Boolean {
        return Build.BRAND?.toLowerCase(Locale.getDefault()).equals("xiaomi")
    }

    override fun request(context: Context) {
        open(
            context = context,
            target = target,
            activity = activity
        )
    }
}