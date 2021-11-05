package test.rxjava;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.util.Objects;


public class HelloRx {
    public static void main(String[] args) throws IOException {
        //被观察对象，事件源
        Observable<String> observable=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                //产生的消息
                observableEmitter.onNext("hello");
                observableEmitter.onNext("www.mashibing.com");
            }
        });

        Consumer<String> consumer=new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(Thread.currentThread().getName()+"---consumer=="+s);
            }
        };

        Observer<String> observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(Thread.currentThread().getName()+"--Observer--"+s);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.observeOn(Schedulers.newThread()).subscribe(consumer);
        Objects.requireNonNull(observer,"observer must not null");
        observable.observeOn(Schedulers.newThread()).subscribe(observer);
        for(;;);
    }
}
