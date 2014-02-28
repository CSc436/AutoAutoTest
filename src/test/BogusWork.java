package test;

public class BogusWork implements Runnable {
    @Override
    public void run() {
        
        int i = 0;
        try {
            System.out.println("before while");
            while(!Thread.interrupted()) {
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
        finally {
            System.out.println("finish");    
        }
        
 
    }
}
