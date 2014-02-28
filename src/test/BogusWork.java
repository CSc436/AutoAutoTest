package test;

public class BogusWork {
    
    public void dumb(Integer param) {

        int i = param;
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("in loop " + i);
            i++;
        }

    }
    
    public void dumber() {

            System.out.println("here and gone");

    }
}
