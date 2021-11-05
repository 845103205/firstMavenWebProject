package test.aop;


import org.springframework.aop.framework.Advised;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
       ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Calculator bean = context.getBean(Calculator.class);
        Calculator calculator=new Calculator();
        calculator.chu(1,1);
        if(bean instanceof Advised){
            System.out.println("_____________________");
        }
        System.out.println(bean.chu(100,1));
    }
}
