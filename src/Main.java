import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean endProgram = false;
        String var;
        List<Account> accounts = Account.getAccounts();
        Employee root = new Employee("root", "111 111 111", "1111", "admin");
        Service cutting = new Service(ServiceType.CUTTING, 30, 30);
        Service styling = new Service(ServiceType.STYLING, 40, 60);
        Service coloring = new Service(ServiceType.COLORING, 50, 90);
        Service washingAndConditioning = new Service(ServiceType.WASHING_AND_CONDITIONING, 40, 45);
//Program---------------------------------------------------------------------------------------------------------------
        while (!endProgram){
            System.out.println("Do you want to Sign in or Sign up? (Sign in or Sign up)");
            var = scanner.nextLine();
            if (var.equalsIgnoreCase("Sign in")){
                System.out.println("Type in your full name");
                String nameInput = scanner.nextLine();

                System.out.println("Type in your PIN:");
                String pinInput = scanner.nextLine();

                Account foundAccount = accounts.stream()
                        .filter(a -> a.getFullName().equals(nameInput)
                                && a.getPIN().equals(pinInput))
                        .findFirst()
                        .orElse(null);
                if (foundAccount != null) {
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Wrong name or PIN!");
                }
            } else if (var.equalsIgnoreCase("Sign up")) {

                System.out.println("Enter your full name:");
                String fullName = scanner.nextLine();

                if (Account.accountExists(fullName)) {
                    System.out.println("An account with this name already exists!");
                    return;
                }

                System.out.println("Enter your phone number:");
                String phoneNumber = scanner.nextLine();

                System.out.println("Create a PIN:");
                String pin = scanner.nextLine();
                new Account(fullName, phoneNumber, pin);

                System.out.println("Account created successfully!");
            }
            else {
                System.out.println("Wrong Input!");
            }
            System.out.println("Do you want to exit? (yes or no)");
            var = scanner.nextLine();
            if (var.equals("yes")){
                endProgram = true;
            }
        }
        scanner.close();
    }
}