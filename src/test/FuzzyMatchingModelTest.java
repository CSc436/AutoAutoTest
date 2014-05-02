package test;

import static org.junit.Assert.*;
import model.FuzzyMatchingModel;

import org.junit.Test;

/**
 * @author Carlos G
 * 
 */
public class FuzzyMatchingModelTest {
    
    /**
     * TODO
     */
    @Test
    public void getRatio() {
        FuzzyMatchingModel model1 = new FuzzyMatchingModel(false, false, false,
                2);
        double blah = model1.getRatio("Hello", "Hell");
        System.out.println(model1.getRatio("Hello", "Hell"));
        assertEquals(.89, blah, 2);
    }

    /**
     * TODO
     */
    @Test
    public void testTwoStringsWithPassingRatio() {
        FuzzyMatchingModel model1 = new FuzzyMatchingModel(false, false, false,
                2);
        boolean answer = model1.getResult("Hello", "Hell", .80);
        assertEquals(answer, true);
    }
    
    /**
     * TODO
     */
    @Test
    public void testTwoStringsWithFailingRatio(){
        FuzzyMatchingModel model1 = new FuzzyMatchingModel(false, false, false,
                2);
        boolean answer = model1.getResult("Hello", "Hell", .90);
        assertEquals(answer, false);
    }
    
    /**
     * 
     */
    @Test
    public void testTwoStringsWithIgnoreCasingAndPassingRatio(){
        FuzzyMatchingModel model1 = new FuzzyMatchingModel(false, false, true,
                2);
//        double result = model1.getRatio("HeLLO", "Hell");
        boolean answer = model1.getResult("HeLLO", "Hell", .80);
//        assertEquals(.89, result, 2);
        assertEquals(answer, true);
    }
    
    /**
     * TODO
     */
    @Test
    public void testTwoStringsWithIgnoreCasingAndFailingRatio(){
        FuzzyMatchingModel model1 = new FuzzyMatchingModel(false, false, true,
                2);
        boolean answer = model1.getResult("HeLLO", "Hell", .90);
        assertEquals(answer, false);
    }
    
    /**
     * TODO
     */
    @Test
    public void testTwoStringsWithIgnoringWhiteSpaceAndPassingRatio(){
        FuzzyMatchingModel model1 = new FuzzyMatchingModel(true, false, false,
                2);
        String one = "hel  l l";
        String two = "hell";
        boolean answer = model1.getResult(one, two, .80);
        assertEquals(answer,true);
    }
    
    /**
     * TODO
     */
    @Test
    public void testTwoStringsWithIgnoringWhiteSpaceAndFailingRatio(){
        FuzzyMatchingModel model1 = new FuzzyMatchingModel(true, false, false,
                2);
        String one = "hel  l l";
        String two = "hell";
        boolean answer = model1.getResult(one, two, .90);
        assertEquals(answer,false);
    }
    
    /**
     * TODO
     */
    @Test
    public void testTwoStringsWithIgnorePunctAndPassingRatio(){
        FuzzyMatchingModel model1 = new FuzzyMatchingModel(false, true, false,
                2);
        boolean answer = model1.getResult("hello!!!", "hell.", .80);
        assertEquals(answer, true);
    }
    
    /**
     * TODO
     */
    @Test
    public void testTwoStringsWithIgnorePunctAndFailingRatio(){
        FuzzyMatchingModel model1 = new FuzzyMatchingModel(false, true, false,
                2);
        boolean answer = model1.getResult("hello!!!", "hell.", .90);
        assertEquals(answer, false);
    }
    
}
