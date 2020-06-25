import org.junit.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Loader extends Thread
{
    private static ExecutorService service = Executors.newFixedThreadPool(4);
    public static void main(String[] args) {
    }
    @Test
    public void getWordsTest() throws InterruptedException {
        System.out.println("Work of the bank started");
        Transferring transferring = new Transferring();
        service.submit(transferring);
        service.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Task completed");
    }
}
