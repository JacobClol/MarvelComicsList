package com.example.marvelcomicslist.di

import com.example.marvelcomicslist.core.utils.CoroutinesContextProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun priveContextProvider(): CoroutinesContextProvider {
        return if (isRunningTest?.get() == true) {
            object : CoroutinesContextProvider {
                override val io: CoroutineContext
                    get() = Dispatchers.Unconfined
                override val main: CoroutineContext
                    get() = Dispatchers.Unconfined
                override val computation: CoroutineContext
                    get() = Dispatchers.Unconfined
                override val immediate: CoroutineContext
                    get() = Dispatchers.Unconfined
            }
        } else {
            object : CoroutinesContextProvider {
                override val io: CoroutineContext
                    get() = Dispatchers.IO
                override val main: CoroutineContext
                    get() = Dispatchers.Main
                override val computation: CoroutineContext
                    get() = Dispatchers.Default
                override val immediate: CoroutineContext
                    get() = Dispatchers.Main.immediate
            }
        }
    }

    var isRunningTest: AtomicBoolean? = null
}