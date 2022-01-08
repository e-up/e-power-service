package me.iamee.power_service.compatibility

import android.content.Context
import android.os.Build
import java.util.*

class HuaweiCompatibility : Compatibility {

    private val targets = arrayListOf(
        listOf(
            "com.huawei.systemmanager",
            "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"
        ),
        listOf(
            "com.huawei.systemmanager",
            "com.huawei.systemmanager.optimize.bootstart.BootStartActivity"
        )
    )

    override fun supported(context: Context): Boolean {
        val brands = arrayOf("huawei", "honor")
        return brands.contains(Build.BRAND?.toLowerCase(Locale.getDefault()))
    }

    override fun request(context: Context) {
        val iterator = targets.iterator()
        while (iterator.hasNext()) {
            val target = iterator.next()
            val result = tryOpen(context = context, target = target[0], activity = target[1])
            if (result) return
        }
    }
}