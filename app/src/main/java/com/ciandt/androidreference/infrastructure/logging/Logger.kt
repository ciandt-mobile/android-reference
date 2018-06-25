package com.ciandt.androidreference.infrastructure.logging

import android.content.Context
import android.os.Build
import com.ciandt.androidreference.BuildConfig
import com.ciandt.androidreference.infrastructure.logging.timber.CrashlyticsTree
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import timber.log.Timber
import java.util.regex.Pattern

object Logger {

    private const val MAX_TAG_LENGTH = 23
    private const val CALL_STACK_INDEX = 2
    private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")

    @JvmStatic
    fun init(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Fabric.with(context, Crashlytics())
            Timber.plant(CrashlyticsTree())
        }
    }

    private fun createStackElementTag(element: StackTraceElement): String {
        var tag = element.className
        val m = ANONYMOUS_CLASS.matcher(tag)
        if (m.find()) {
            tag = m.replaceAll("")
        }
        tag = tag.substring(tag.lastIndexOf('.') + 1)

        // Limit of tag size removed on API 24
        return if (tag.length <= MAX_TAG_LENGTH || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tag
        } else {
            tag.substring(
                0,
                MAX_TAG_LENGTH
            )
        }
    }

    private fun tag(): String {
        val stackTrace = Throwable().stackTrace
        if (stackTrace.size <= CALL_STACK_INDEX) {
            throw IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?")
        }
        return createStackElementTag(
            stackTrace[CALL_STACK_INDEX]
        )
    }

    //region ### Verbose

    @JvmStatic
    fun v(message: String?, vararg args: Any? = arrayOf()) {
        Timber.tag(tag()).v(message, *args)
    }

    @JvmStatic
    fun v(t: Throwable, message: String?, vararg args: Any? = arrayOf()) {
        Timber.tag(tag()).v(t, message, *args)
    }

    @JvmStatic
    fun v(t: Throwable) {
        Timber.tag(tag()).v(t)
    }

    //endregion

    //region ### Debug

    @JvmStatic
    fun d(message: String?, vararg args: Any? = arrayOf()) {
        Timber.tag(tag()).d(message, *args)
    }

    @JvmStatic
    fun d(t: Throwable, message: String?, vararg args: Any? = arrayOf()) {
        Timber.tag(tag()).d(t, message, *args)
    }

    @JvmStatic
    fun d(t: Throwable) {
        Timber.tag(tag()).d(t)
    }

    //endregion

    //region ### Info

    @JvmStatic
    fun i(message: String?, vararg args: Any? = arrayOf()) {
        Timber.tag(tag()).i(message, *args)
    }

    @JvmStatic
    fun i(t: Throwable, message: String?, vararg args: Any? = arrayOf()) {
        Timber.tag(tag()).i(t, message, *args)
    }

    @JvmStatic
    fun i(t: Throwable) {
        Timber.tag(tag()).i(t)
    }

    //endregion

    //region ### Warning

    @JvmStatic
    fun w(message: String?, vararg args: Any? = arrayOf()) {
        Timber.tag(tag()).w(message, *args)
    }

    @JvmStatic
    fun w(t: Throwable, message: String?, vararg args: Any? = arrayOf()) {
        Timber.tag(tag()).w(t, message, *args)
    }

    @JvmStatic
    fun w(t: Throwable) {
        Timber.tag(tag()).w(t)
    }

    //endregion

    //region ### Error

    @JvmStatic
    fun e(message: String?, vararg args: Any? = arrayOf()) {
        Timber.tag(tag()).e(message, *args)
    }

    @JvmStatic
    fun e(t: Throwable, message: String?, vararg args: Any? = arrayOf()) {
        Timber.tag(tag()).e(t, message, *args)
    }

    @JvmStatic
    fun e(t: Throwable) {
        Timber.tag(tag()).e(t)
    }

    //endregion

    //region ### Asset

    @JvmStatic
    fun wtf(message: String?, vararg args: Any? = arrayOf()) {
        Timber.tag(tag()).wtf(message, *args)
    }

    @JvmStatic
    fun wtf(t: Throwable, message: String?, vararg args: Any? = arrayOf()) {
        Timber.tag(tag()).wtf(t, message, *args)
    }

    @JvmStatic
    fun wtf(t: Throwable) {
        Timber.tag(tag()).wtf(t)
    }

    //endregion
}