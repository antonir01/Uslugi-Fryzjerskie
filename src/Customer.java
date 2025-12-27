public class Customer extends Account {
    public Customer(String fullName, String phoneNumber, String PIN) {
        super(fullName, phoneNumber, PIN);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", PIN='" + PIN + '\'' +
                '}';
    }
}