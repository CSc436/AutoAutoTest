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
            Class<BogusWork> theClass = runningClass;
            Method theMethod = runningMethod;
            
            System.out.println(theClass.toString() + "::::" + theMethod.toString());
            
            args = new Object[] {new Integer(1)};
            try {
                theMethod.invoke(BogusWork.class, 0);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    
    @Test
    public void doingStuff() {

        
        final BogusWork ban = new BogusWork();
        runningClass = BogusWork.class;
        try {
            runningMethod = runningClass.getMethod("dumber");
        } catch (Exception e2) {
            e2.printStackTrace();
        } 
        
        System.out.println(runningClass.toString() + "::::" + runningMethod.toString());
        
        try {
            runningMethod.invoke(ban.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }

        
//        Thread get = new Thread() {
//            @Override
//            public void run() {
//                ban.dumb(0);
//            }
//        };
//        get.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        System.out.println("before interupt");
//        get.stop();
//        System.out.println("after interupt");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        System.out.println("end of prog");
    }
}
