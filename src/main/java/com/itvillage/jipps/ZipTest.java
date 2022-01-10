package com.itvillage.jipps;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ZipTest {
    public static void main(String[] args) throws InterruptedException {

        Deque deque = new ArrayDeque(IntStream.range(0, 10).boxed().collect(Collectors.toList()));

        Mono<Tuple2> resultMono = getMono(deque);

        resultMono.map(x -> {
          System.out.println("t1:" + x.getT1() + ", t2:" + x.getT2());
          return x;
        }).subscribe();


        // System.out.println(getMono(deque).block());

    }

    public static Mono<?> getMono(Deque<Integer> deque) {
        Integer intVal = deque.poll();
        System.out.println("intVal:" + intVal);

        Mono<String> mono =  Mono.just("" + intVal);;
        if (intVal == 5) {
            // mono = Mono.empty();
            mono = Mono.error(new RuntimeException("Error"));
        }

        return mono
                .onErrorResume(x -> {
                    return Mono.just(x.getLocalizedMessage());
                })
                .zipWhen(x -> {
            if (!deque.isEmpty()) {
                return getMono(deque);
            } else {
                return Mono.just("End");
            }
        });
    }

    public static String getThreadName(){
        return Thread.currentThread().getName();
    }
}
