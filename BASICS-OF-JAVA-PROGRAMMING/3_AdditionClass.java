import java.util.Scanner;

class AdditionClass 
{
	int num1;
	int num2;
	public void setNumbers(int n1, int n2) 
	{
        	num1 = n1;
        	num2 = n2;
	}
	public int calculateSum() 
	{
        	return num1 + num2;
	}
}
class AddTwoNumbers 
{
	public static void main(String[] args) 
	{
        	Scanner input = new Scanner(System.in);
        	AdditionClass myObj = new AdditionClass();

        	System.out.println("Enter the first integer:");
        	int firstNum = input.nextInt();

        	System.out.println("Enter the second integer:");
        	int secondNum = input.nextInt();

        	myObj.setNumbers(firstNum, secondNum);
        	int sum = myObj.calculateSum();

        	System.out.println("The sum is: " + sum);
        	input.close();
	}
}  