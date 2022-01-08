package me.iamee.power_service.compatibility

import android.content.Context
import android.os.Build
import java.util.*

class OPPOCompatibility : Compatibility {

    private val targets = arrayListOf<String>("com.oppo.safe", "com.coloros.oppoguardelf", "com.coloros.safecenter")

    override fun supported(context: Context): Boolean {
        return Build.BRAND?.toLowerCase(Locale.getDefault()) == "oppo"
    }

    override fun request(context: Context) {
        val iterator = targets.iterator()
        while (iterator.hasNext()) {
            val target = iterator.next()
            val result = tryOpen(context = context, target = target)
            if (result) return
        }
    }


}