package com.gaps.sample.runner;

import com.gaps.sample.benchmark.BenchmarkHello;
import com.gaps.sample.benchmark.BenchmarkLoops;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkApp {
    public static void main(String... args) {
        try {
            Options opt = new OptionsBuilder()
                    .include(BenchmarkHello.class.getSimpleName())
                    .include(BenchmarkLoops.class.getSimpleName())
                    .warmupIterations(5)
                    .measurementIterations(30)
                    .forks(1)
                    .build();

            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }

}
