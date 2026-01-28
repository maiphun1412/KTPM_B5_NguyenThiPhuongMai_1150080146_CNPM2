package B2;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SuiteTest1 {

    public String message = "Fpoly";
    JUnitMessage junitMessage = new JUnitMessage(message);

    // Test 1: mong đợi ArithmeticException (giống mẫu thầy)
    @Test(expected = ArithmeticException.class)
    public void testJUnitMessage() {
        System.out.println("JUnit Message is printing");
        junitMessage.printMessage(); // hàm này cố tình chia 0
    }

    // Test 2: kiểm tra chuỗi "Hi!" + message (giống mẫu thầy)
    @Test
    public void testHiMessage() {
        message = "Hi!" + message;
        System.out.println("JUnit Hi Message is printing");

        assertEquals(message, junitMessage.printHiMessage());
        System.out.println("Suite Test 2 is successful " + message);
    }
}
