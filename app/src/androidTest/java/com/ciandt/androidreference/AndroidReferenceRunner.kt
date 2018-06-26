package com.ciandt.androidreference

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import com.github.tmurakami.dexopener.DexOpener

class AndroidReferenceRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        DexOpener.install(this)
        return super.newApplication(cl, AndroidReference::class.java.name, context)
    }
}