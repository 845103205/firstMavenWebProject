package likou.threadlocaldemo.asyncthread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
@Service
public class executeTask {
    @Async
    public Future executeTask(int age){
        System.out.println(age);
        return new  AsyncResult(age);
    }
}
