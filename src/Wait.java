import java.util.concurrent.TimeUnit;

public class Wait {
    public static void delay() {
        class Inf {
            public void get_inf() {
                System.out.println("Error n21");
            }
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Inf myInf = new Inf();
            myInf.get_inf();
        }
    }
}