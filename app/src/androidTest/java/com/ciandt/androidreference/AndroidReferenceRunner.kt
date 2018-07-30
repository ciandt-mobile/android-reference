package com.ciandt.androidreference

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import com.github.tmurakami.dexopener.DexOpener

class AndroidReferenceRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        // TODO: DexOpener apresenta problemas com o novo databinding. Testes espressos param de funcionar
        //DexOpener.install(this)
        return super.newApplication(cl, AndroidReference::class.java.name, context)
    }
}