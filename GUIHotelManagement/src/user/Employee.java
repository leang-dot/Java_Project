package user;

public class Employee extends User {
    private static int employeeCount = 0;
    protected int employeeID;
    protected String employeeRole;
    protected Double salary;
    protected String address;
    protected String dateOfBirth;
    protected String hireDate;
    protected String workStatus;
    protected String workSchedule;

    public Employee(int employeeID, String employeeRole, Double salary, String address, String dateOfBirth,
            String hireDate, String workStatus, String workSchedule, String firstName, String lastName,
            String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
        this.employeeID = employeeID;
        this.employeeRole = employeeRole;
        this.salary = salary;
        this.address = address;
        this.hireDate = hireDate;
        this.workStatus = workStatus;
        this.workSchedule = workSchedule;
        employeeCount++;
    }

    // employee methods

    public static int getEmployeeCount() {
        return employeeCount;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public void setWorkSchedule(String workSchedule) {
        this.workSchedule = workSchedule;
    }

}

