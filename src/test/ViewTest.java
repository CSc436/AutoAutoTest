package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import view.View;

/**
 * Tests for the View Class
 * 
 * @author dillon
 * 
 */
public class ViewTest {

    /**
     * Runs all of the tests in this class.
     * 
     */
    @Test
    public void testAll() {
        testViewCreation();
    }

    /**
     * Tests the creation of the View class.
     * 
     */
    @Test
    public void testViewCreation() {
        View view = new View();
        assertEquals(view, view);
    }

}
