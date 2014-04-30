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
    public void testTwoStrings() {
        FuzzyMatchingModel model1 = new FuzzyMatchingModel(false, false, false,
                2);
        double result = model1.getRatio("Hello", "Hell");
        assertEquals(.89, result, 2);
    }

}
