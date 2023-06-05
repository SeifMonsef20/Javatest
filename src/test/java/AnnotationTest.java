import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class AnnotationTest {
    @Test
    public void methodTest0() {
        System.out.println("Using @Test,it can be executed as a test case");
    }
    @Test
    @Timeout(10)
    public void methodTest1() {
        System.out.println("Using @Test(timeout), it can be used to enforce timeout in test case");    }
    @Test
    public void methodTest2() {
        System.out.println(" Using @Test,it can be executed as a test case ");    }
}
