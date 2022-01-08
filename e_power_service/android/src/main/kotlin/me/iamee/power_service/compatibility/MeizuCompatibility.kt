package me.iamee.power_service.compatibility

import android.content.Context
import android.os.Build
import java.util.*

class MeizuCompatibility : Compatibility {

    private val target = "com.meizu.safe"

    override fun supported(context: Context): Boolean {
        return Build.BRAND?.toLowerCase(Locale.getDefault()) == "meizu"
    }

    override fun request(context: Context) {
        tryOpen(context = context, target = target)
    }
}