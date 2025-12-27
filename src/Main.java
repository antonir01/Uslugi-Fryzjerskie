import java.time.LocalDate;
import java.util.List;

public class Main {
    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static final List<Account> accounts = Account.getAccounts();
    private static final List<Stock> stockList = Stock.getStockList();
    private static final Service cutting = new Service(ServiceType.CUTTING, 30, 30);
    private static final Service styling = new Service(ServiceType.STYLING, 40, 60);
    private static final Service coloring = new Service(ServiceType.COLORING, 50, 90);
    private static final Service washingAndConditioning = new Service(ServiceType.WASHING_AND_CONDITIONING, 40, 45);

    public static void main(String[] args) {
        // Create root admin account
        Employee root = new Employee("root", "111 111 111", "1111", "admin");
        accounts.add(root);

        boolean endProgram = false;
        while (!endProgram) {
            Account loggedInAccount = loginOrSignup();
            if (loggedInAccount != null) {
                System.out.println("Welcome, " + loggedInAccount.getFullName());
                if (loggedInAccount instanceof Employee employee) {
                    if (employee.getPosition().equalsIgnoreCase("admin")) {
                        adminMenu();
                    } else {
                        employeeMenu();
                    }
                } else if (loggedInAccount instanceof Customer customer) {
                    customerMenu(customer);
                }
            }

            System.out.println("Do you want to exit? (yes or no)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                endProgram = true;
            }
        }
    }

    private static Account loginOrSignup() {
        System.out.println("Do you want to Sign in or Sign up? (Sign in or Sign up)");
        String var = scanner.nextLine();

        if (var.equalsIgnoreCase("Sign in")) {
            System.out.println("Type in your full name:");
            String name = scanner.nextLine();
            System.out.println("Type in your PIN:");
            String pin = scanner.nextLine();

            Account account = accounts.stream()
                    .filter(a -> a.getFullName().equals(name) && a.getPIN().equals(pin))
                    .findFirst()
                    .orElse(null);

            if (account != null) {
                System.out.println("Login successful!");
                return account;
            } else {
                System.out.println("Wrong name or PIN!");
            }
        } else if (var.equalsIgnoreCase("Sign up")) {
            System.out.println("Enter your full name:");
            String fullName = scanner.nextLine();
            if (Account.accountExists(fullName)) {
                System.out.println("An account with this name already exists!");
                return null;
            }

            System.out.println("Enter your phone number:");
            String phone = scanner.nextLine();

            System.out.println("Create a PIN:");
            String pin = scanner.nextLine();

            Customer customer = new Customer(fullName, phone, pin);
            System.out.println("Account created successfully!");
            return customer;
        } else {
            System.out.println("Wrong Input!");
        }
        return null;
    }

    private static void adminMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Create employee account");
            System.out.println("2. Logout");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> createEmployee();
                case "2" -> {
                    exit = true;
                    System.out.println("Logged out.");
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void employeeMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- EMPLOYEE MENU ---");
            System.out.println("1. Show reservations");
            System.out.println("2. Manage stock");
            System.out.println("3. Logout");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> Reservation.showReservations();
                case "2" -> manageStock();
                case "3" -> {
                    exit = true;
                    System.out.println("Logged out.");
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void customerMenu(Customer customer) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- CUSTOMER MENU ---");
            System.out.println("1. Make reservation");
            System.out.println("2. View my reservations");
            System.out.println("3. Logout");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> makeReservation(customer);
                case "2" -> {
                    boolean hasReservations = false;
                    for (Reservation r : Reservation.getAllReservations()) {
                        if (r.getCustomer().equals(customer)) {
                            System.out.println(r);
                            hasReservations = true;
                        }
                    }
                    if (!hasReservations) System.out.println("No reservations found.");
                }
                case "3" -> {
                    exit = true;
                    System.out.println("Logged out.");
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void createEmployee() {
        System.out.println("Enter employee full name:");
        String name = scanner.nextLine();
        if (Account.accountExists(name)) {
            System.out.println("Account already exists!");
            return;
        }
        System.out.println("Enter phone number:");
        String phone = scanner.nextLine();
        System.out.println("Create PIN:");
        String pin = scanner.nextLine();
        System.out.println("Enter position (employee/admin):");
        String position = scanner.nextLine();
        Employee employee = new Employee(name, phone, pin, position);
        accounts.add(employee);
        System.out.println("Employee account created successfully!");
    }

    private static void manageStock() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- STOCK MANAGEMENT ---");
            System.out.println("1. View stock");
            System.out.println("2. Add stock");
            System.out.println("3. Back");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    if (stockList.isEmpty()) System.out.println("No stock available.");
                    else stockList.forEach(s -> System.out.println(s.name + " | " + s.stockType + " | $" + s.price));
                }
                case "2" -> {
                    System.out.println("Enter stock name:");
                    String name = scanner.nextLine();
                    StockType type = null;
                    while (type == null) {
                        System.out.println("Enter stock type (CONDITIONER, DYE, GEL):");
                        String input = scanner.nextLine().trim().toUpperCase();
                        try {
                            type = StockType.valueOf(input);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid stock type!");
                        }
                    }
                    System.out.println("Enter price:");
                    double price = Double.parseDouble(scanner.nextLine());
                    new Stock(name, type, price);
                    System.out.println("Stock added successfully!");
                }
                case "3" -> exit = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void makeReservation(Customer customer) {
        System.out.println("Choose service:");
        System.out.println("1. Cutting");
        System.out.println("2. Styling");
        System.out.println("3. Coloring");
        System.out.println("4. Washing and conditioning");
        Service selected = null;
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> selected = cutting;
            case "2" -> selected = styling;
            case "3" -> selected = coloring;
            case "4" -> selected = washingAndConditioning;
            default -> System.out.println("Invalid service!");
        }
        if (selected == null) return;

        System.out.println("Enter reservation date (YYYY-MM-DD):");
        LocalDate date;
        try {
            date = LocalDate.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid date format!");
            return;
        }

        System.out.println("Choose payment method (CASH, PAYPAL, TRANSFER):");
        PaymentMethod paymentMethod = null;
        while (paymentMethod == null) {
            try {
                paymentMethod = PaymentMethod.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid payment method!");
            }
        }

        new Reservation(customer, selected, date, paymentMethod);
        System.out.println("Reservation created successfully!");
    }
}
