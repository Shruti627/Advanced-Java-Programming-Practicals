class PrimeNumber {
    int value;

    boolean isPrime() {
        if (value <= 1) return false;

        for (int i = 2; i <= value; i++) {
            if (value % i == 0)
                return false;
        }
        return true;
    }
}

class PrimeNo {
    public static void main(String[] args) {
        PrimeNumber num = new PrimeNumber();
        num.value = 30;
        boolean ans = num.isPrime();

        if (ans == true) {
            System.out.println(num.value + " is prime");
        } else {
            System.out.println(num.value + " is not prime");
        }

        System.out.println("another number");
        num.value = 7;

        if (num.isPrime()) {
            System.out.println(num.value + " is prime");
        } else {
            System.out.println(num.value + " is not prime");
        }
    }
}
