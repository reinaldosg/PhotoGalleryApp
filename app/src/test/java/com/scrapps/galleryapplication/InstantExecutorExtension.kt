package com.scrapps.galleryapplication

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor

//class InstantExecutorExtension : BeforeEachCallback, AfterEachCallback {
//    override fun beforeEach(context: ExtensionContext?) {
//        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
//            override fun executeOnDiskIO(runnable: Runnable) = runnable.run()
//            override fun postToMainThread(runnable: Runnable) = runnable.run()
//            override fun isMainThread(): Boolean = true
//        })
//    }
//
//    override fun afterEach(context: ExtensionContext?) {
//        ArchTaskExecutor.getInstance().setDelegate(null)
//    }
//}