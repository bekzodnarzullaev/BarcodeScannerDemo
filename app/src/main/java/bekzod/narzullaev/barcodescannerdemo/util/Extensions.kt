package bekzod.narzullaev.barcodescannerdemo.util

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}

fun checkPermissionIsGranted(context: Context, permission: String): Boolean {
    val res: Int = context.checkCallingOrSelfPermission(permission)
    return res == PackageManager.PERMISSION_GRANTED
}

fun Activity.shouldShowRationalPermission(permission: String): Boolean {
    return shouldShowRequestPermissionRationale(permission)
}

fun copyToClipboard(context: Context, text: String) {
    if (text.isBlank()) {
        return
    }

    val clipboard: ClipboardManager? =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    val clip = ClipData.newPlainText("label", text)
    clipboard?.setPrimaryClip(clip)
}