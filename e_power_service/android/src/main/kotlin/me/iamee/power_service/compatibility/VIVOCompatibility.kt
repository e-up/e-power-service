package me.iamee.power_service.compatibility

import android.content.Context
import android.os.Build
import java.util.*

class VIVOCompatibility : Compatibility {

    val target = "com.iqoo.secure"

    override fun supported(context: Context): Boolean {
        return Build.BRAND?.toLowerCase(Locale.getDefault()) == "vivo"
    }

    override fun request(context: Context) {
        tryOpen(context = context, target = target)
    }
}