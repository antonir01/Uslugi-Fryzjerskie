public class Employee extends Account {
    String position;

    public Employee(String fullName, String phoneNumber, String PIN, String position) {
        super(fullName, phoneNumber, PIN);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
