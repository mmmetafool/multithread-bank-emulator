import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

class Bank
{
    private ConcurrentHashMap<Integer, Account> accounts =new ConcurrentHashMap<>();
    private final Random random = new Random();

    private synchronized boolean isFraud(Integer fromAccountNum, Integer toAccountNum, long amount) throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    void fillAccounts() {
        for (int i = 0; i<10;i++) {
            Account acc = new Account();
            acc.setIsBlocked(false);
            acc.setAccNumber(i);
            int value = (int) (Math.random() * 1000000);
            acc.setMoney(value);
            accounts.put(i,acc);
        }
    }
    private void transferMoney(Integer fromAccountNum, Integer toAccountNum, long amount) throws InterruptedException {
        if (amount>50000) {
            if (isFraud(fromAccountNum,toAccountNum,amount)) {
                accounts.get(fromAccountNum).setIsBlocked(true);
                accounts.get(toAccountNum).setIsBlocked(true);
                System.out.println("One or more accounts were blocked due to fraud operations");
            }
        }
        if (!(accounts.get(fromAccountNum).getIsBlocked()) && !(accounts.get(toAccountNum).getIsBlocked())) {
            long buff = accounts.get(fromAccountNum).getMoney();
            accounts.get(fromAccountNum).setMoney(buff - amount);
            buff = accounts.get(toAccountNum).getMoney();
            accounts.get(toAccountNum).setMoney(buff + amount);
            System.out.println("Total amount of " + amount + " was transferred from " + fromAccountNum + " to " + toAccountNum);
        }
    }
    void syncTransfer(Integer fromAccountNum, Integer toAccountNum, long amount) throws InterruptedException {
        if (fromAccountNum < toAccountNum){
            synchronized (fromAccountNum) {
                synchronized (toAccountNum) {
                    transferMoney(fromAccountNum,toAccountNum, amount);
                }
            }
        } else
            synchronized (toAccountNum) {
                synchronized (fromAccountNum) {
                    transferMoney(fromAccountNum,toAccountNum, amount);
                }
            }
    }

    ConcurrentHashMap getAccounts() {
        return accounts;
    }
}
