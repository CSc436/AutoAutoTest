package test;

import java.lang.reflect.Method;
import org.junit.Test;

public class JunkTest {

    private Class<BogusWork> runningClass;
    private Method runningMethod;
    private Object[] args;
    
    public class ThreadStopper extends Thread {
        @Override 
        public void run() {
              
        }
    }
    
    
    @Test
    public void doingStuff() {

        
        final BogusWork ban = new BogusWork();
        
        Thread get = new Thread() {
            @Override
            public void run() {
                ban.dumb(0);
            }
        };
        get.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println("before interupt");
        if(get.isAlive()) {
            System.out.println("its alive");
            get.stop();
        }
        System.out.println("after interupt");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("end of prog");
    }
}
