package model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests the Args wrapper class.
 * @author Ricky
 *
 */
public class ArgsTest {

    /**
     * Test empty constructor.
     */
    @Test
    public void argsEmptyConstructorTest() {
        Args args = new Args();
        assertTrue(args.getArgsValue().equals(""));
    }

    /**
     * Test setter.
     */
    @Test
    public void argsSetterTest() {
        Args args = new Args();
        args.setArgsValue("x, false, 3.14");
        assertTrue(args.getArgsValue().equals("x, false, 3.14"));
    }

    /**
     * Test parameterized constructor.
     */
    @Test
    public void argsParameterizedConstructorTest() {
        Args args = new Args("\"indubitably\"");
        assertTrue(args.getArgsValue().equals("\"indubitably\""));
    }

    /**
     * Test toString.
     */
    @Test
    public void argsToStringTest() {
        Args args = new Args("bant");
        assertTrue(args.toString().equals("bant"));
    }

}
