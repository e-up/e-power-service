package me.iamee.power_service

import android.content.Context
import android.os.PowerManager
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import me.iamee.power_service.compatibility.*

/** EPowerServicePlugin */
class EPowerServicePlugin : FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel

    private lateinit var context: Context

    private val compatibilities: List<Compatibility> = listOf(
        BaseCompatibility(),
        HuaweiCompatibility(),
        MeizuCompatibility(),
        OPPOCompatibility(),
        SamsungCompatibility(),
        VIVOCompatibility(),
        XiaomiCompatibility()
    )

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        context = flutterPluginBinding.applicationContext
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "e_power_service")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "isGranted" -> isGranted(call, result)
            "request" -> request(call, result)
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    private fun isGranted(call: MethodCall, result: Result) {
        val manager: PowerManager? =
            context.getSystemService(Context.POWER_SERVICE) as PowerManager?
        val isGranted =
            manager?.run { this.isIgnoringBatteryOptimizations(context.packageName) } ?: false
        result.success(isGranted)
    }

    private fun request(call: MethodCall, result: Result) {
        val supportedCompatibilities = compatibilities.filter { it.supported(context = context) }
        supportedCompatibilities.onEach { it.request(context = context) }
    }
}
