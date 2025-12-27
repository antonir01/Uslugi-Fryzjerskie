import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean endProgram = false;
        String var;
        Account loggedInAccount = null;
        List<Account> accounts = Account.getAccounts();
        List<Stock> stockList = Stock.getStockList();
        Employee root = new Employee("root", "111 111 111", "1111", "admin");
        accounts.add(root);
        Service cutting = new Service(ServiceType.CUTTING, 30, 30);
        Service styling = new Service(ServiceType.STYLING, 40, 60);
        Service coloring = new Service(ServiceType.COLORING, 50, 90);
        Service washingAndConditioning = new Service(ServiceType.WASHING_AND_CONDITIONING, 40, 45);
        while (!endProgram) {
            System.out.println("Do you want to Sign in or Sign up? (Sign in or Sign up)");
            var = scanner.nextLine();
            if (var.equalsIgnoreCase("Sign in")) {
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
                    loggedInAccount = foundAccount;
                } else {
                    System.out.println("Wrong name or PIN!");
                }
            } else if (var.equalsIgnoreCase("Sign up")) {

                System.out.println("Enter your full name:");
                String fullName = scanner.nextLine();

                if (Account.accountExists(fullName)) {
                    System.out.println("An account with this name already exists!");
                    continue;
                }

                System.out.println("Enter your phone number:");
                String phoneNumber = scanner.nextLine();

                System.out.println("Create a PIN:");
                String pin = scanner.nextLine();
                loggedInAccount = new Customer(fullName, phoneNumber, pin);
                System.out.println("Account created successfully!");
            } else {
                System.out.println("Wrong Input!");
            }
            if (loggedInAccount != null) {
                System.out.println("Welcome, " + loggedInAccount.getFullName());

                if (loggedInAccount instanceof Employee) {
                    Employee employee = (Employee) loggedInAccount;

                    if (employee.getPosition().equalsIgnoreCase("admin")) {
                        boolean adminMenu = true;

                        while (adminMenu) {
                            System.out.println("\n--- ADMIN MENU ---");
                            System.out.println("1. Create employee account");
                            System.out.println("2. Logout");

                            String choice = scanner.nextLine();

                            switch (choice) {
                                case "1":
                                    System.out.println("Enter employee full name:");
                                    String empName = scanner.nextLine();

                                    if (Account.accountExists(empName)) {
                                        System.out.println("Account already exists!");
                                        break;
                                    }

                                    System.out.println("Enter phone number:");
                                    String empPhone = scanner.nextLine();

                                    System.out.println("Create PIN:");
                                    String empPin = scanner.nextLine();

                                    System.out.println("Enter position (employee/admin):");
                                    String position = scanner.nextLine();

                                    Employee newEmployee = new Employee(empName, empPhone, empPin, position);
                                    accounts.add(newEmployee);

                                    System.out.println("Employee account created successfully!");
                                    break;

                                case "2":
                                    loggedInAccount = null;
                                    adminMenu = false;
                                    System.out.println("Logged out.");
                                    break;

                                default:
                                    System.out.println("Invalid choice!");
                            }
                        }
                    } else {
                        boolean employeeMenu = true;

                        while (employeeMenu) {
                            System.out.println("\n--- EMPLOYEE MENU ---");
                            System.out.println("1. Show reservations");
                            System.out.println("2. Manage stock");
                            System.out.println("3. Logout");

                            String choice = scanner.nextLine();

                            switch (choice) {
                                case "1":
                                    Reservation.showReservations();
                                    break;

                                case "2":
                                    boolean stockMenu = true;

                                    while (stockMenu) {
                                        System.out.println("\n--- STOCK MANAGEMENT ---");
                                        System.out.println("1. View stock");
                                        System.out.println("2. Add stock");
                                        System.out.println("3. Back");

                                        String stockChoice = scanner.nextLine();

                                        switch (stockChoice) {
                                            case "1":
                                                if (stockList.isEmpty()) {
                                                    System.out.println("No stock available.");
                                                } else {
                                                    for (Stock stock : stockList) {
                                                        System.out.println(
                                                                stock.name + " | " +
                                                                        stock.stockType + " | $" +
                                                                        stock.price
                                                        );
                                                    }
                                                }
                                                break;

                                            case "2":
                                                System.out.println("Enter stock name:");
                                                String stockName = scanner.nextLine();

                                                StockType stockType = null;

                                                while (stockType == null) {
                                                    System.out.println("Enter stock type (CONDITIONER, DYE, GEL):");
                                                    String typeInput = scanner.nextLine().trim().toUpperCase();

                                                    try {
                                                        stockType = StockType.valueOf(typeInput);
                                                    } catch (IllegalArgumentException e) {
                                                        System.out.println("Invalid stock type!");
                                                    }
                                                }


                                                System.out.println("Enter price:");
                                                double price = Double.parseDouble(scanner.nextLine());

                                                new Stock(stockName, stockType, price);
                                                System.out.println("Stock added successfully!");
                                                break;

                                            case "3":
                                                stockMenu = false;
                                                break;

                                            default:
                                                System.out.println("Invalid choice!");
                                        }
                                    }
                                    break;

                                case "3":
                                    loggedInAccount = null;
                                    employeeMenu = false;
                                    System.out.println("Logged out.");
                                    break;

                                default:
                                    System.out.println("Invalid choice!");
                            }

                        }
                    }

                }
            } else {
                boolean customerMenu = true;
                Customer customer = (Customer) loggedInAccount;

                while (customerMenu) {
                    System.out.println("\n--- CUSTOMER MENU ---");
                    System.out.println("1. Make reservation");
                    System.out.println("2. View my reservations");
                    System.out.println("3. Logout");

                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            System.out.println("Choose service:");
                            System.out.println("1. Cutting");
                            System.out.println("2. Styling");
                            System.out.println("3. Coloring");
                            System.out.println("4. Washing and conditioning");

                            Service selectedService = null;
                            String serviceChoice = scanner.nextLine();

                            switch (serviceChoice) {
                                case "1":
                                    selectedService = cutting;
                                    break;
                                case "2":
                                    selectedService = styling;
                                    break;
                                case "3":
                                    selectedService = coloring;
                                    break;
                                case "4":
                                    selectedService = washingAndConditioning;
                                    break;
                                default:
                                    System.out.println("Invalid service!");
                                    break;
                            }

                            if (selectedService == null) break;

                            System.out.println("Enter reservation date (YYYY-MM-DD):");
                            LocalDate date;

                            try {
                                date = LocalDate.parse(scanner.nextLine());
                            } catch (Exception e) {
                                System.out.println("Invalid date format!");
                                break;
                            }

                            System.out.println("Choose payment method (CASH, PAYPAL, TRANSFER):");
                            PaymentMethod paymentMethod = null;

                            while (paymentMethod == null) {
                                try {
                                    paymentMethod = PaymentMethod.valueOf(
                                            scanner.nextLine().trim().toUpperCase()
                                    );
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid payment method!");
                                }
                            }


                            new Reservation(customer, selectedService, date, paymentMethod);
                            System.out.println("Reservation created successfully!");
                            break;

                        case "2":
                            boolean hasReservations = false;
                            for (Reservation r : Reservation.getAllReservations()) {
                                if (r.getCustomer().equals(customer)) {
                                    System.out.println(r);
                                    hasReservations = true;
                                }
                            }
                            if (!hasReservations) {
                                System.out.println("No reservations found.");
                            }
                            break;

                        case "3":
                            loggedInAccount = null;
                            customerMenu = false;
                            System.out.println("Logged out.");
                            break;

                        default:
                            System.out.println("Invalid choice!");
                    }
                }
            }

            System.out.println("Do you want to exit? (yes or no)");
            var = scanner.nextLine();
            if (var.equalsIgnoreCase("yes")) {
                endProgram = true;
            }
        }
        scanner.close();
    }
}