package B2;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SuiteTest1.class,
        SuiteTest2.class
})
public class JUnitTest {

    // Thêm main để VS Code Run Java chạy được
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(JUnitTest.class);

        System.out.println("So test chay: " + result.getRunCount());
        System.out.println("So test loi: " + result.getFailureCount());

        for (Failure f : result.getFailures()) {
            System.out.println(f.toString());
        }

        System.out.println("Thanh cong: " + result.wasSuccessful());
    }

}
