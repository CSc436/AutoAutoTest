package test;

import static org.junit.Assert.assertEquals;
import model.Args;

import org.junit.Test;

/**
 * Tests the Args wrapper class.
 * 
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
        assertEquals("", args.getArgsValue());
    }

    /**
     * Test setter.
     */
    @Test
    public void argsSetterTest() {
        Args args = new Args();
        args.setArgsValue("x, false, 3.14");
        assertEquals("x, false, 3.14", args.getArgsValue());
    }

    /**
     * Test parameterized constructor.
     */
    @Test
    public void argsParameterizedConstructorTest() {
        Args args = new Args("\"indubitably\"");
        assertEquals("\"indubitably\"", args.getArgsValue());
    }

    /**
     * Test toString.
     */
    @Test
    public void argsToStringTest() {
        Args args = new Args("bant");
        assertEquals("bant", args.getArgsValue());
    }

}
