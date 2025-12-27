import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Reservation {
    Customer customer;
    Service service;
    LocalDate date;
    PaymentMethod paymentMethod;

    private static List<Reservation> reservations = new ArrayList<>();

    public static List<Reservation> getAllReservations() {
        return reservations;
    }

    public Customer getCustomer() {
        return customer;
    }


    public Reservation(Customer customer, Service service, LocalDate date, PaymentMethod paymentMethod) {
        this.customer = customer;
        this.service = service;
        this.date = date;
        this.paymentMethod = paymentMethod;
        reservations.add(this);
    }

    public static void showReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }
        for (Reservation reservation : reservations) {
            System.out.println(reservation.toString());
        }
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", service=" + service +
                ", date=" + date +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
