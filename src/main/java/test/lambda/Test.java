package test.lambda;

import java.util.function.Predicate;

public class Test {
    public static void main(String[] args) {
        Predicate<String> pre=(username)->{
            return "admin".equals(username);
        };

        boolean admin = pre.test("admin");
        System.out.println(admin);
    }
}
