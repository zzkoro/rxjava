package com.itvillage.jipps;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.stream.Stream;

public class FlatMapTest {
    public static void main(String[] args) throws InterruptedException {

        String[][] sample = new String[][]{
                {"a", "b"}, {"c", "d"}, {"e", "a"}, {"a", "h"}, {"i", "j"}
        };

        Stream<String[]> stream = Arrays.stream(sample);
        Stream<String> stream1 = stream.flatMap(Arrays::stream).parallel();
        stream1.forEach(el -> System.out.println(getThreadName() + ": el:" + el));

//        Thread.sleep(5000);
    }

    public static String getThreadName(){
        return Thread.currentThread().getName();
    }
}
