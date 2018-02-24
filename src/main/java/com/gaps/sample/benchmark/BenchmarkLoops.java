package com.gaps.sample.benchmark;

import org.openjdk.jmh.annotations.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchmarkLoops {

    private static List<String> STRING_ARRAY = new ArrayList<>();
    private static List<BigDecimal> DECIMAL_ARRAY = new ArrayList<>();

    static {
        String[] strings = new String[1000];
        Arrays.fill(strings, "ay nako what to do");
        STRING_ARRAY.addAll(Arrays.asList(strings));

        BigDecimal[] decimals = new BigDecimal[1000];
        Arrays.fill(decimals, BigDecimal.valueOf(12345L));
        DECIMAL_ARRAY.addAll(Arrays.asList(decimals));
    }

    @Benchmark
    public void forLoop() {
        for (int i = 0; i < STRING_ARRAY.size(); i++) {
            String string = STRING_ARRAY.get(i);
            doStuff(string);
        }
    }

    @Benchmark
    public void nestedForLoop() {
        for (int i = 0; i < STRING_ARRAY.size(); i++) {
            String string = STRING_ARRAY.get(i);
            doStuff(string);
            for (int j = 0; j < DECIMAL_ARRAY.size(); j++) {
                BigDecimal decimal = DECIMAL_ARRAY.get(j);
                doMathStuff(decimal);
            }
        }
    }

    @Benchmark
    public void lambdaLoop() {
        STRING_ARRAY.forEach(s -> doStuff(s));
    }

    @Benchmark
    public void nestedLambdaLoop() {
        STRING_ARRAY.forEach(s -> {
            doStuff(s);
            DECIMAL_ARRAY.forEach(d -> doMathStuff(d));
        });
    }

    private void doMathStuff(BigDecimal decimal) {
        decimal.abs();
        BigDecimal newDecimal = decimal.add(BigDecimal.TEN);
        newDecimal.compareTo(decimal);
        newDecimal.divide(BigDecimal.ONE);
        decimal.divideAndRemainder(newDecimal);
    }


    private void doStuff(String string) {
        doStringStuff(string);
        doObjectStuff(string);
        doObjectStuff(string);
        doObjectStuff(string);
        doStringStuff(string);
        doStringStuff(string);
    }

    private void doStringStuff(String string) {
        string.length();
        string.substring(0, 1);
        String newString = string.concat(" concat concat concat");
        newString.length();
        newString.compareTo(string);
        newString.indexOf("k");
        string.contains("bacon");
    }

    private void doObjectStuff(String string) {
        Object o = new Object();
        o.hashCode();
        o = new Object();
        o.toString();
        o = new Object();
        o.equals(string);
        if ((Math.random() * 100) > 50) {
            o = null;
        }
        if (Objects.nonNull(o)) {
            o.toString();
        }
    }
}
