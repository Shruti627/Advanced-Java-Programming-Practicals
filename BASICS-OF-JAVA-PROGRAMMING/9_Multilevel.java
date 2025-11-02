class Shape {
    protected String color;

    Shape(String color) {
        this.color = color;
    }

    public void display() {
        System.out.println("This is a " + color + " shape");
    }
}

class Rectangle extends Shape {
    public int len, wid;

    Rectangle(String color, int len, int wid) {
        super(color);
        this.len = len;
        this.wid = wid;
    }

    public void display() {
        System.out.println("This is a " + color + " rectangle with length " + len + " and width " + wid);
    }

    public int getArea() {
        return len * wid;
    }
}

class Square extends Rectangle {
    public Square(String color, int side) {
        super(color, side, side);
    }

    public void display() {
        System.out.println("This is a " + color + " square with side " + len);
    }

    public int getPerimeter() {
        return 4 * len;
    }
}

class Inheritance {
    public static void main(String[] args) {
        Shape shape = new Shape("blue");
        Rectangle rectangle = new Rectangle("red", 12, 2);
        Square square = new Square("green", 5);

        System.out.println("Level - 1");
        shape.display();

        System.out.println("Level - 2");
        rectangle.display();
        System.out.println("Area of rectangle: " + rectangle.getArea());

        System.out.println("Level - 3");
        square.display();
        System.out.println("Area of square: " + square.getArea());
        System.out.println("Perimeter of square: " + square.getPerimeter());

        // Polymorphism
        System.out.println();
        System.out.println("By polymorphism");
        Shape[] shapes = {shape, rectangle, square};

        for (Shape s : shapes) {
            s.display();
        }
    }
}
