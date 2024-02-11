package com.github.dhslrl321.springcache.support

object SleepUtils {
    fun sleep() {
        for (i in 1..3) {
            Thread.sleep(1000L)
            println("loading... ($i)")
        }
    }
}
