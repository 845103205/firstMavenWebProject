package likou.threadlocaldemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RestController
public class statController {

    static Set<Val<Integer>> set = new HashSet<>();

    static synchronized void addSet(Val<Integer> val) {
        set.add(val);
    }

    static ThreadLocal<Val<Integer>> threadLocal = new ThreadLocal<Val<Integer>>() {
        @Override
        protected Val<Integer> initialValue() {
            Val<Integer> val = new Val<>();
            val.setT(0);
            //向set中插入元素可能會引发线程安全问题。
            addSet(val);
            return val;
        }
    };

    void _add() {
        try {
            Val<Integer> val = threadLocal.get();
            val.setT(val.getT() + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("stat")
    public Object stat() {  //取出每一個元素
        Objects.requireNonNull(set);
        return set.stream().map(x->x.getT()).reduce((a,x)->a+x).get();
    }

    @RequestMapping("add")
    public Object add() {
        _add();
        return "ok";
    }

}
