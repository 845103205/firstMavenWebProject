package test.list;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        List<Person> list = new ArrayList();
        list.add(new Person(20,"t0"));
        list.add(new Person(21,"t1"));
        list.add(new Person(22,"t2"));
        list.add(new Person(22,"t2"));
        list.add(new Person(null,"t3"));

        Object[] collect = list.stream().filter(s->s.age != null).map(s->s.getAge()+1).distinct().limit(4).toArray();

        Stream<Object> collect1 = Stream.of(collect);
        List<Object> collect2 = collect1.collect(Collectors.toList());
        System.out.println(collect2);


    }


}
@Data
class Person{
    Integer age;
    String name;

    private Person() {
    }

    public Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }
}
