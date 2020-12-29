package junit;

import org.junit.runner.JUnitCore;

import javax.xml.transform.Result;

public class Main {
    public static void main(String[] args) {
        Math math = new Math();
        System.out.println(math.addition(1,5));

//        Result result = JUnitCore.runClasses();
    }

    public static void threadSleep() throws InterruptedException {
        Thread.sleep(300);
    }
}
