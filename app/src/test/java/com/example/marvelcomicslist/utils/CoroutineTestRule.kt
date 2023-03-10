package com.example.marvelcomicslist.utils

import com.example.marvelcomicslist.core.utils.CoroutinesContextProvider
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class CoroutineTestRule : TestRule {

    private val job = SupervisorJob()
    val testDispatcher = TestCoroutineDispatcher()
    val coroutineContextProvider = object : CoroutinesContextProvider {
        override val io: CoroutineContext
            get() = testDispatcher
        override val main: CoroutineContext
            get() = testDispatcher
        override val computation: CoroutineContext
            get() = testDispatcher
        override val immediate: CoroutineContext
            get() = testDispatcher

    }

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement(){
            override fun evaluate() {
                Dispatchers.setMain(testDispatcher)
                base?.evaluate()
                job.cancel()
                Dispatchers.resetMain()
            }
        }
    }
}