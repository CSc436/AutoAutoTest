package test;

import static org.junit.Assert.assertEquals;
import model.FuzzyMatchingModel;

import org.junit.Test;

/**
 * @author Carlos G
 *
 */
public class FuzzyMatchingTest {
    
    /**
     * 
     */
    @Test
    public void testTwoString(){
        
        FuzzyMatchingModel model1 = new FuzzyMatchingModel();
        double result = model1.getRatio("Hello", "Hell");
        // ((5+4)-1)/(5+4) => (9-1)/9 => 8/9 should be .89
        assertEquals(.89, result,2);
    }
    
    @Test
    public void testTwoStringsNull(){
        FuzzyMatchingModel model1 = new FuzzyMatchingModel();
        double result = model1.getRatio("Hello", "Hell");
        
    }

}
