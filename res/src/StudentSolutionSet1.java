import java.util.Scanner;

public class StudentSolutionSet1 {
    
    public int add1(int num) {
        return num + 1;
    }
    
    public String appendCom(String str) {
        return str + ".com";
    }
    
    public boolean opposite(boolean val) {
        return !val;
    }
    
    public void helloWorld() {
        System.out.print("Hello World!");
    }
    
    public int badAdd1(int num) {
        return num - 1;
    }
    
    public String badAppendCom(String str) {
        return str + ".gov";
    }
    
    public boolean badOpposite(boolean val) {
        return val;
    }
    
    public void badHelloWorld() {
        System.out.print("Goodbye World!");
    }

    public void convert() {
        System.out.print("Enter length in inches: ");
        Scanner keyboard = new Scanner(System.in);
        double length = keyboard.nextDouble();
        double result = length * 2.54;
        System.out.println("Length in cm: " + result);
    }
    
}
