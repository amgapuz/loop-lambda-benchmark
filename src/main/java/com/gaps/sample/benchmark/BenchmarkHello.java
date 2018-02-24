package com.gaps.sample.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchmarkHello {

    @Benchmark
    public void printHello() {
        System.out.println("Hello World!");
    }

}
