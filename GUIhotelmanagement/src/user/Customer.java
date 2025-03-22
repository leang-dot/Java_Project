package user;

public class Customer extends User {
    private int customerId;

    public Customer(int customerId, String username, String firstName, String lastName, String phoneNumber, String email, String password) {
        super(username, firstName, lastName, phoneNumber, email, password);
        if (customerId < 0) {
            throw new IllegalArgumentException("Customer ID cannot be negative.");
        }
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }
}