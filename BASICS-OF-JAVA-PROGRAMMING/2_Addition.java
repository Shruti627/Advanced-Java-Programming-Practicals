import java.util.Scanner;

class Addition
{

	public static void main(String []args)
	{
	int sum,ans;
	 int a=10;
	 int b=20;
	 sum=a+b;
	 System.out.println("Addition :"+sum);
	
	Scanner sc=new Scanner(System.in);

	int x,y;
	System.out.println("Enter 1st number : ");
	x=sc.nextInt();
	System.out.println("Enter 2nd number : ");
	y=sc.nextInt();
	ans=x+y;
	System.out.println("Addition : "+ans);




	}

}