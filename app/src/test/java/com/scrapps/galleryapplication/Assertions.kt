package com.scrapps.galleryapplication

import androidx.appcompat.app.AppCompatActivity
import org.junit.Assert
import org.robolectric.Robolectric
import org.robolectric.Shadows
import kotlin.reflect.KClass
import org.junit.Assert.*

infix fun KClass<out AppCompatActivity>.shouldNavigateTo(nextActivity: KClass<out AppCompatActivity>): () -> Unit = {
    val originActivity = Robolectric.buildActivity(this.java).get()
    val shadowActivity = Shadows.shadowOf(originActivity)
    val nextIntent = shadowActivity.peekNextStartedActivity()


    Assert.assertEquals(nextIntent.component?.className,nextActivity.java.canonicalName)
}