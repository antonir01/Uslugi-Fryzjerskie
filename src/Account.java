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

    public static List<Account> getAccounts() {
        return accounts;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPIN() {
        return PIN;
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

    public static boolean accountExists(String fullName) {
        return accounts.stream()
                .anyMatch(a -> a.getFullName().equalsIgnoreCase(fullName));
    }

}
