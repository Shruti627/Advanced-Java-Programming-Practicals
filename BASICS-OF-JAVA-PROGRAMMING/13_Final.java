final class Vehicle //Final Class
{
    private String model;
    private String brand;

    public Vehicle(String model, String brand) {
        this.model = model;
        this.brand = brand;
    }

    public void displayDetails() {
        System.out.println("Model: " + model + ", Brand: " + brand);
    }
}

class Circle {
    private final double PI = 3.14159; //Final Variable
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return PI * radius * radius;
    }

    public final void MyFinalMethod() {
        System.out.println("Final Method Calling");
    }
}

class Main {
    public static void main(String[] args) {
        Vehicle myCar = new Vehicle("Model XYZ", "Tesla");
        myCar.displayDetails();

        Circle circle = new Circle(5.0);
        double area = circle.calculateArea();
        System.out.println("Area of the circle: " + area);
        circle.MyFinalMethod();
    }
}
