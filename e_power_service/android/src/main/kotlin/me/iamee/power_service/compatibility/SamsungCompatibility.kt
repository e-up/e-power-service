package me.iamee.power_service.compatibility

import android.content.Context
import android.os.Build
import java.util.*

class SamsungCompatibility : Compatibility {

    private val targets = arrayOf("com.samsung.android.sm_cn", "com.samsung.android.sm")

    override fun supported(context: Context): Boolean {
        return Build.BRAND?.toLowerCase(Locale.getDefault()) == "samsung"
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