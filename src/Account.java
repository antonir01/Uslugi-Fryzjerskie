import java.util.ArrayList;
import java.util.List;

public class Account {
    String fullName;
    String phoneNumber;
    String PIN;

    private static List<Account> accounts = new ArrayList<>();
    public Account(String fullName, String phoneNumber, String PIN) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.PIN = PIN;
        accounts.add(this);
    }
    public static void showAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No account found.");
            return;
        }
        for (Account account : accounts) {
            System.out.println(account.toString());
        }
    }
}
