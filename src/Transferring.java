import java.util.concurrent.ConcurrentHashMap;


public class Transferring implements Runnable
{
    private static Bank bank = new Bank();
    private ConcurrentHashMap<Integer, Account> accounts =new ConcurrentHashMap<>();
    private final Object lock = new Object();
    private int money = 51000;

    @Override
    public void run() {
        bank.fillAccounts();
        accounts = bank.getAccounts();
        System.out.println("Accounts filled");
        for (Integer key : accounts.keySet()){
            if (key != accounts.size()-1) {
                try {
                    synchronized (lock) {
                        bank.syncTransfer(accounts.get(key).getAccNumber(), accounts.get(key + 1).getAccNumber(), money);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else System.out.println("The bank ended its work");
        }
    }
}
