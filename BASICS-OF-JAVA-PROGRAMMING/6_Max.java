import java.util.Scanner;

class Maximum {
    public int searchMax(int n1, int n2, int n3) {
        int max;

        if (n1 >= n2 && n1 >= n3) {
            max = n1;
        } else if (n2 >= n1 && n2 >= n3) {
            max = n2;
        } else {
            max = n3;
        }

        return max;
    }
}

class MaxMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Maximum obj = new Maximum();

        System.out.println("Enter 3 numbers :");
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();

        int m = obj.searchMax(num1, num2, num3);
        System.out.println("Max : " + m);

        sc.close();
    }
}
