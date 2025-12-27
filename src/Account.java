import java.util.ArrayList;
import java.util.List;

public class Account {
    String fullName;
    String phoneNumber;
    String PIN;

    private static final List<Account> accounts = new ArrayList<>();

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

    public static boolean accountExists(String fullName) {
        return accounts.stream()
                .anyMatch(a -> a.getFullName().equalsIgnoreCase(fullName));
    }

    @Override
    public String toString() {
        return "Account{" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", PIN='" + PIN + '\'' +
                '}';
    }
}
