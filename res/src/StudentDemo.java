
public class DemoStudent {

    public int factorial(int n) {
        if (n < 2) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public greetUser() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = keyboard.nextLine();
        System.out.println("Hello " + name);
    }

    public void infiniteLoop() {
        while (true) {
            System.out.println("lololol");
        }
    }

}
