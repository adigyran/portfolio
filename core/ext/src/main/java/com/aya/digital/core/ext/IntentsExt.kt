package com.aya.digital.core.ext

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

fun Context.browse(url: String, newTask: Boolean = false): Boolean {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        if (newTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
        return true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        return false
    }
}


fun Context.shareApp(text: String): Boolean {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, text)
        intent.putExtra(Intent.EXTRA_TEXT,
            "https://play.google.com/store/apps/details?id=" + this.packageName)
        startActivity(Intent.createChooser(intent, text))
        return true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        return false
    }

}

fun Context.openAppStore() : Boolean
{
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
        return true
    } catch (e: ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
        return false
    }
}

fun Context.openAnotherAppStore(packageName:String) : Boolean
{
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
        return true
    } catch (e: ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
        return false
    }
}
fun Context.share(text: String, subject: String = ""): Boolean {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, null))
        return true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        return false
    }
}

fun Context.email(email: String, subject: String = "", text: String = ""): Boolean {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:")
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
    if (subject.isNotEmpty())
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    if (text.isNotEmpty())
        intent.putExtra(Intent.EXTRA_TEXT, text)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
        return true
    }
    return false

}

fun Context.makeCall(number: String): Boolean {
    try {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        startActivity(intent)
        return true
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
}

fun Context.openPhone(number: String): Boolean =
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        startActivity(intent)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

fun Context.sendSMS(number: String, text: String = ""): Boolean {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$number"))
        intent.putExtra("sms_body", text)
        startActivity(intent)
        return true
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
}

fun Context.openWhatsApp(number: String) {
    val whatsAppPackage = "com.whatsapp"
    val whatsAppBusinessPackage = "com.whatsapp.w4b"
    val isClassicInstalled: Boolean = isSomePackageInstalled(
        this,
        whatsAppPackage
    )
    val isBusinessInstalled: Boolean = isSomePackageInstalled(
        this,
        whatsAppBusinessPackage
    )
    if (isClassicInstalled || isBusinessInstalled) {
        val whatsApp = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://api.whatsapp.com/send?phone=$number")
        ).apply { setPackage(if (isBusinessInstalled) whatsAppBusinessPackage else whatsAppPackage) }
        startActivity(
            Intent.createChooser(
                whatsApp,
                "Open with"
            )
        )
    } else {
        val whatsApp = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=$whatsAppPackage")
        )
        startActivity(whatsApp)
    }
}


private fun isSomePackageInstalled(context: Context, packageName: String) =
    runCatching {
        context.packageManager.getPackageInfo(
            packageName,
            PackageManager.GET_META_DATA
        )
    }.isSuccess
