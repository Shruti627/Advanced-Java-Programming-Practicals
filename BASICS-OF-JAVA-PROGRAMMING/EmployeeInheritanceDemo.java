// Parent class (Superclass)
class Employee {
    protected String name;
    protected int id;
    protected double salary;
    protected String department;
    
    // Constructor
    public Employee(String name, int id, double salary, String department) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.department = department;
    }
    
    // Method to display basic info
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Salary: Rs/-" + salary);
        System.out.println("Department: " + department);
    }
    
    // Method that can be overridden
    public void work() {
        System.out.println(name + " is working on general tasks");
    }
    
    // Method to calculate annual salary
    public double getAnnualSalary() {
        return salary * 12;
    }
    
    // Method for lunch break
    public void takeLunchBreak() {
        System.out.println(name + " is taking a lunch break");
    }
}

// Child class 1 (Subclass)
class Manager extends Employee {
    private int teamSize;
    private String projectName;
    
    // Constructor
    public Manager(String name, int id, double salary, String department, 
                   int teamSize, String projectName) {
        super(name, id, salary, department); // Call parent constructor
        this.teamSize = teamSize;
        this.projectName = projectName;
    }
    
    // Override parent method
    @Override
    public void work() {
        System.out.println(name + " is managing the team and overseeing project: " + projectName);
    }
    
    // Additional method specific to Manager
    public void conductMeeting() {
        System.out.println(name + " is conducting a team meeting with " + teamSize + " members");
    }
    
    // Method to assign tasks
    public void assignTasks() {
        System.out.println(name + " is assigning tasks for project: " + projectName);
    }
    
    // Override displayInfo to include manager-specific details
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call parent method
        System.out.println("Team Size: " + teamSize + " members");
        System.out.println("Project: " + projectName);
        System.out.println("Position: Manager");
    }
    
    // Manager gets bonus
    @Override
    public double getAnnualSalary() {
        double bonus = salary * 0.2; // 20% bonus
        return super.getAnnualSalary() + (bonus * 12);
    }
}

// Child class 2 (Subclass)
class Developer extends Employee {
    private String programmingLanguage;
    private int experienceYears;
    
    // Constructor
    public Developer(String name, int id, double salary, String department, 
                     String programmingLanguage, int experienceYears) {
        super(name, id, salary, department); // Call parent constructor
        this.programmingLanguage = programmingLanguage;
        this.experienceYears = experienceYears;
    }
    
    // Override parent method
    @Override
    public void work() {
        System.out.println(name + " is coding in " + programmingLanguage + 
                          " and solving technical problems");
    }
    
    // Additional method specific to Developer
    public void debugCode() {
        System.out.println(name + " is debugging " + programmingLanguage + " code");
    }
    
    // Method to review code
    public void reviewCode() {
        System.out.println(name + " is reviewing code written by team members");
    }
    
    // Override displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call parent method
        System.out.println("Programming Language: " + programmingLanguage);
        System.out.println("Experience: " + experienceYears + " years");
        System.out.println("Position: Developer");
    }
    
    // Senior developers get experience bonus
    @Override
    public double getAnnualSalary() {
        if (experienceYears >= 5) {
            double experienceBonus = salary * 0.1 * experienceYears; // 10% per year bonus
            return super.getAnnualSalary() + (experienceBonus * 12);
        }
        return super.getAnnualSalary();
    }
}

// Child class 3 (Subclass)
class Intern extends Employee {
    private String university;
    private int duration; // in months
    
    // Constructor
    public Intern(String name, int id, double salary, String department, 
                  String university, int duration) {
        super(name, id, salary, department);
        this.university = university;
        this.duration = duration;
    }
    
    // Override parent method
    @Override
    public void work() {
        System.out.println(name + " is learning and assisting with basic tasks");
    }
    
    // Additional method specific to Intern
    public void attendTraining() {
        System.out.println(name + " is attending training sessions");
    }
    
    // Method for learning
    public void learnSkills() {
        System.out.println(name + " is learning new skills and gaining experience");
    }
    
    // Override displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("University: " + university);
        System.out.println("Internship Duration: " + duration + " months");
        System.out.println("Position: Intern");
    }
}

// Additional child class - SeniorDeveloper (Multi-level inheritance)
class SeniorDeveloper extends Developer {
    private boolean isTeamLead;
    private String specialization;
    
    // Constructor
    public SeniorDeveloper(String name, int id, double salary, String department, 
                          String programmingLanguage, int experienceYears, 
                          boolean isTeamLead, String specialization) {
        super(name, id, salary, department, programmingLanguage, experienceYears);
        this.isTeamLead = isTeamLead;
        this.specialization = specialization;
    }
    
    // Override work method
    @Override
    public void work() {
        System.out.println(name + " is leading development work and mentoring junior developers");
    }
    
    // Additional method specific to SeniorDeveloper
    public void mentorJuniors() {
        System.out.println(name + " is mentoring junior developers");
    }
    
    // Method for architecture design
    public void designArchitecture() {
        System.out.println(name + " is designing system architecture for " + specialization);
    }
    
    // Override displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call Developer's displayInfo
        System.out.println("Team Lead: " + (isTeamLead ? "Yes" : "No"));
        System.out.println("Specialization: " + specialization);
        System.out.println("Position: Senior Developer");
    }
    
    // Senior developers get leadership bonus
    @Override
    public double getAnnualSalary() {
        double leadBonus = isTeamLead ? salary * 0.15 * 12 : 0; // 15% leadership bonus
        return super.getAnnualSalary() + leadBonus;
    }
}

// Main class to demonstrate inheritance
class EmployeeInheritanceDemo {
    public static void main(String[] args) {
        System.out.println("=== Employee Management System - Inheritance Example ===\n");
        
        // Create objects of different classes
        Employee genericEmployee = new Employee("Mr. A", 1001, 3000, "General");
        Manager projectManager = new Manager("Mr. B", 1002, 8000, "IT", 8, "E-commerce Platform");
        Developer javaDeveloper = new Developer("Mr. C", 1003, 6000, "IT", "Java", 4);
        Intern collegeIntern = new Intern("Mr. D", 1004, 1500, "Marketing", "State University", 6);
        SeniorDeveloper techLead = new SeniorDeveloper("Mr. E", 1005, 9000, "IT", "Python", 8, true, "Machine Learning");
        
        // Demonstrate inheritance with Manager
        System.out.println("--- Manager Example ---");
        projectManager.displayInfo();
        projectManager.work();
        projectManager.conductMeeting();
        projectManager.assignTasks();
        System.out.println("Annual Salary: Rs/-" + projectManager.getAnnualSalary());
        System.out.println();
        
        // Demonstrate inheritance with Developer
        System.out.println("--- Developer Example ---");
        javaDeveloper.displayInfo();
        javaDeveloper.work();
        javaDeveloper.debugCode();
        javaDeveloper.reviewCode();
        javaDeveloper.takeLunchBreak(); // Inherited method
        System.out.println("Annual Salary: Rs/-" + javaDeveloper.getAnnualSalary());
        System.out.println();
        
        // Demonstrate inheritance with Intern
        System.out.println("--- Intern Example ---");
        collegeIntern.displayInfo();
        collegeIntern.work();
        collegeIntern.attendTraining();
        collegeIntern.learnSkills();
        System.out.println();
        
        // Demonstrate multi-level inheritance with SeniorDeveloper
        System.out.println("--- Senior Developer Example ---");
        techLead.displayInfo();
        techLead.work();
        techLead.debugCode(); // Inherited from Developer
        techLead.mentorJuniors(); // SeniorDeveloper specific method
        techLead.designArchitecture();
        System.out.println("Annual Salary: Rs/-" + techLead.getAnnualSalary());
        System.out.println();
        
        // Demonstrate polymorphism
        System.out.println("--- Polymorphism Example ---");
        Employee[] employees = {genericEmployee, projectManager, javaDeveloper, collegeIntern, techLead};
        
        System.out.println("All employees working:");
        for (Employee emp : employees) {
            System.out.print("- ");
            emp.work(); // Different behavior based on actual object type
        }
        System.out.println();
        
        // Salary comparison
        System.out.println("Annual Salary Comparison:");
        for (Employee emp : employees) {
            System.out.println("- " + emp.name + ": Rs/- " + emp.getAnnualSalary());
        }
    }
}
