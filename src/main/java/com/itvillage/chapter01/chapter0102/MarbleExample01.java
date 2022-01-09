package com.itvillage.chapter01.chapter0102;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class MarbleExample01 {
    public static void main(String[] args){
        Observable.just(1, 25, 9, 15, 7, 30)
                .filter(x -> x > 10)
                .subscribeOn(Schedulers.io())
                .subscribe(x -> System.out.println(x));
    }
}
