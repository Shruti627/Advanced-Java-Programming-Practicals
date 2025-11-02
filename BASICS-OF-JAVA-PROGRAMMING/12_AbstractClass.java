
abstract class Employee {

    protected String name;
    protected int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public abstract double calculateSalary();

    public void displayInfo() {
        System.out.println("Employee : " + name + " ID : " + id);
    }

}

class FullTimeEmployee extends Employee {

    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    public double calculateSalary() {
        return monthlySalary;
    }

}

class PartTimeEmployee extends Employee {

    private double hourlyRate;
    private double hoursWorked;

    public PartTimeEmployee(String name, int id, double hourlyRate, double hoursWorked) {
        super(name, id);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }

}

class EmployeeMain {

    public static void main(String[] args) {
        Employee[] employees = {
            new FullTimeEmployee("Shruti", 101, 900000),
            new PartTimeEmployee("Sheetal", 23, 1000, 7)
        };
        for (Employee emp : employees) {
			System.out.println();
            emp.displayInfo();
            System.out.println("Salary : " + emp.calculateSalary());
            System.out.println();

        }

    }

}
