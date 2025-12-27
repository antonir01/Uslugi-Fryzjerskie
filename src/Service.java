public class Service {
    ServiceType serviceType;
    double price;
    int duration;

    public Service(ServiceType serviceType, double price, int duration) {
        this.serviceType = serviceType;
        this.price = price;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceType=" + serviceType +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}
