package test;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

public class JunkTest {

    
    @Test
    public void doingStuff() {
        BogusWork ban = new BogusWork();
        Thread get = new Thread(ban);
        get.start();
        System.out.println("here");
        System.out.println("here bant");
        System.out.println("here again");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println("before interupt");
        get.stop();
        System.out.println("after interupt");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
