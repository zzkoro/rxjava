package com.itvillage.chapter02;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class HelloRxJava {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> observable1 = Observable.just("Hello1", "RxJava-1!")
        .doOnNext(data -> {
            System.out.println(getThreadName() + ": doOnNext: " + data);
        });
                ;
//        observable1.doOnNext(data -> {
//            System.out.println(getThreadName() + ": doOnNext: " + data);
//        });
        observable1.subscribeOn(Schedulers.io());
        observable1.observeOn(Schedulers.io());

        observable1.subscribe(data -> {
            System.out.println(getThreadName() + ":" + data);
        });

        Observable observable2 = Observable.just("Hello2", "RxJava-2!");
        observable2.subscribe(data -> {
            System.out.println(getThreadName() + ": subscibe:" + data);
        });

        Thread.sleep(500);

    }

    public static String getThreadName(){
        return Thread.currentThread().getName();
    }
}
