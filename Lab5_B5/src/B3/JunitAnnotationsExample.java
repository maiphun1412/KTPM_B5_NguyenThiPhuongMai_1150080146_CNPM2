package B3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class JunitAnnotationsExample {

    private ArrayList<String> list;

    @BeforeClass
    public static void m1() {
        System.out.println("Using @BeforeClass , executed before all test cases");
    }

    @Before
    public void m2() {
        list = new ArrayList<String>();
        System.out.println("Using @Before annotations , executed before each test cases");
    }

    @AfterClass
    public static void m3() {
        System.out.println("Using @AfterClass , executed after all test cases");
    }

    @After
    public void m4() {
        list.clear();
        System.out.println("Using @After , executed after each test cases");
    }

    @Test
    public void m5() {
        list.add("test");
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    @Ignore
    @Test
    public void m6() {
        System.out.println("Using @Ignore , this execution is ignored");
    }

    @Test(timeout = 1000)
    public void m7() throws Exception {
        System.out.println("Using @Test(timeout) - test must finish within 1000ms");
        Thread.sleep(50); // đảm bảo không timeout
    }

    @Test(expected = ArithmeticException.class)
    public void m8() {
        System.out.println("Using @Test(expected) - expecting ArithmeticException");
        int x = 10 / 0; // cố tình ném ArithmeticException
        System.out.println(x);
    }
}
