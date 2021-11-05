package test.thread.thread1;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Test {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future> taskList=new ArrayList<Future>();
        for (int i = 0; i < 1000; i++) {
            taskList.add(println(i));
        }


        List results =new ArrayList();
        for (Future future : taskList) {
          results.add(future.get());
        }
        System.out.println("_-------------------------");
        for (Object result : results) {
            System.out.println(result);
        }
    }

    public static Future println(int age){
        System.out.println(age);
        return new AsyncResult(age);
    }
}
