package com.ui.baselineprofile

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class StartUpBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startupWithNoBaseLine() = benchmarkRule.measureRepeated(
        packageName =  InstrumentationRegistry.getArguments().getString("targetAppId")
            ?: throw Exception("targetAppId not passed as instrumentation runner arg"),
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        compilationMode = CompilationMode.None(),
        setupBlock = {
            // Press home button before each run to ensure the starting activity isn't visible.
            pressHome()
        }
    ) {
        // starts default launch activity
        startActivityAndWait()
    }

    @Test
    fun startupWithBaseLine() = benchmarkRule.measureRepeated(
        packageName =  InstrumentationRegistry.getArguments().getString("targetAppId")
            ?: throw Exception("targetAppId not passed as instrumentation runner arg"),
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        compilationMode = CompilationMode.Partial(),
        setupBlock = {
            // Press home button before each run to ensure the starting activity isn't visible.
            pressHome()
        }
    ) {
        // starts default launch activity
        startActivityAndWait()
    }

    @Test
    fun startupWithAOTCompilation() = benchmarkRule.measureRepeated(
        packageName =  InstrumentationRegistry.getArguments().getString("targetAppId")
            ?: throw Exception("targetAppId not passed as instrumentation runner arg"),
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        compilationMode = CompilationMode.Full(),
        setupBlock = {
            // Press home button before each run to ensure the starting activity isn't visible.
            pressHome()
        }
    ) {
        // starts default launch activity
        startActivityAndWait()
    }
}