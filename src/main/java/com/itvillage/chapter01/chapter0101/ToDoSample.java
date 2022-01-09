package com.itvillage.chapter01.chapter0101;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ToDoSample {
    public static void main(String[] args) throws InterruptedException {
        Observable obs1 = Observable.just(100, 200, 300, 400, 500)
                .doOnNext(data -> System.out.println(getThreadName() + " : " + "#doOnNext() : " + data))
                .filter(number -> number > 10)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io());
//                .subscribe(num -> {
//                    System.out.println(getThreadName() + " : result : " + num);
//                });

        System.out.println(getThreadName() + " :" + "Before subscibe!!");

        obs1.subscribe(num -> {
            System.out.println(getThreadName() + " : result : " + num);
        });

        Thread.sleep(500);

        System.out.println(getThreadName() + " :" + "End of Code!!");

    }

    public static String getThreadName(){
        return Thread.currentThread().getName();
    }
}
