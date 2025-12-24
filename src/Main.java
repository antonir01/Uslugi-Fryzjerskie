import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean endProgram = false;
        String var;
        String var2;
        Account root1 = new Employee("root1", "111 111 111", "1111", "admin");
        Employee root = new Employee("root", "111 111 111", "1111", "admin");
        Service cutting = new Service(ServiceType.CUTTING, 30, 30);
        Service styling = new Service(ServiceType.STYLING, 40, 60);
        Service coloring = new Service(ServiceType.COLORING, 50, 90);
        Service washingAndConditioning = new Service(ServiceType.WASHING_AND_CONDITIONING, 40, 45);
//Program---------------------------------------------------------------------------------------------------------------
        while (!endProgram){
            Account.showAccounts();
            System.out.println("Do you want to Sign in or Sign up? (Sign in or Sign up)");
            var = scanner.nextLine();
            if (var.equals("Sign in")){
                System.out.println("Type in your full name");
                var = scanner.nextLine();
                System.out.println("Type in your PIN");
                var2 = scanner.nextLine();
            } else if (var.equals("Sign up")) {

            } else {
                System.out.println("Wrong Input!");
            }
            System.out.println("Do you want to exit? (yes or no)");
            var = scanner.nextLine();
            if (var.equals("yes")){
                endProgram = true;
            }
        }
    }
}