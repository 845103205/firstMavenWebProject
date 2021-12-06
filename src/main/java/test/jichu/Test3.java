package test.jichu;

import java.math.BigDecimal;

/**
 * @author tanjian
 * @date 2021/12/3 17:34
 */
public class Test3 {
    public static void main(String[] args) {
        BigDecimal bd = BigDecimal.ONE;
        bd = bd.subtract(BigDecimal.valueOf(0.1));
        bd = bd.subtract(BigDecimal.valueOf(0.1));
        bd = bd.subtract(BigDecimal.valueOf(0.1));
        bd = bd.subtract(BigDecimal.valueOf(0.1));
        bd = bd.subtract(BigDecimal.valueOf(0.1));

        System.out.println(bd);
        System.out.println(1.0-0.1-0.1-0.1-0.1-0.1);

        BigDecimal bd1 = BigDecimal.valueOf(0.1);
        BigDecimal var = BigDecimal.valueOf(1.0/10.0);
        System.out.println(bd1.equals(var));
    }
}
