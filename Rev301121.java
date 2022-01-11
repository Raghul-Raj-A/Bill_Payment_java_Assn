import java.util.Scanner;

/*Construct a class Student with rollno and noof arrears. create user defined exception
class to throw exception object if no of arrears is greater than5 
which prints the rollno and "Counselling Required".
Accomplish the above task with appropriate interface with relevant components*/
class Student
{
    int rollno,noa;
    public void getdetails()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Roll Number: ");
        rollno = scan.nextInt();
        System.out.println("Enter Number of Arrears: ");
        noa = scan.nextInt();
    } 
    public void putdetails()
    {
        System.out.println("\n"+"\trollno"+"\tnoa");
    }
}
class ArrearException extends Exception
{	
	public ArrearException(String s)
	{System.out.println(s);}
}