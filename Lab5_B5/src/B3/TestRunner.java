package B3;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(JunitAnnotationsExample.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("So test chay: " + result.getRunCount());
        System.out.println("So test loi: " + result.getFailureCount());
        System.out.println("So test bo qua: " + result.getIgnoreCount());
        System.out.println("Thanh cong: " + result.wasSuccessful());
    }
}
