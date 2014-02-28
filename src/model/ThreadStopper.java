package model;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Test;

import test.JunkTest;

public class ThreadStopper {

    
    @Test
    public void needStop() {
        
        
        Thread d = new Thread() {
            public void run() {
                
            }
        };
        
//        JunkTest hg = new JunkTest();
//        hg.getClass().getMethods();
//        for(Method bot: hg) {
//            System.out.println(bot.getName());
//            }
    }
}
