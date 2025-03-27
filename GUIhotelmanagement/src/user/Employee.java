package user;

public class Employee extends User {
    private int employeeId;
    private String role;
    private double salary;
    private String dateOfBirth;
    private String hireDate;

    public Employee(int employeeId, String role, double salary, String dateOfBirth, String hireDate,
                    String firstName, String lastName, 
                    String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
        this.employeeId = employeeId;
        this.role = role;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.hireDate = hireDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getRole() {
        return role;
    }

    public double getSalary() {
        return salary;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getHireDate() {
        return hireDate;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public String getWorkSchedule() {
        return workSchedule;
    }
}