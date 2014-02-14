package model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArgsTest {

    /**
     * Test empty constructor.
     */
    @Test
    public void ArgsEmptyConstructorTest() {
        Args args = new Args();
        assertTrue(args.getArgsValue().equals(""));
    }

    /**
     * Test settter.
     */
    @Test
    public void ArgsSetterTest() {
        Args args = new Args();
        args.setArgsValue("x, false, 3.14");
        assertTrue(args.getArgsValue().equals("x, false, 3.14"));
    }

    /**
     * Test parameterized constructor.
     */
    @Test
    public void ArgsParameterizedConstructorTest() {
        Args args = new Args("\"indubitably\"");
        assertTrue(args.getArgsValue().equals("\"indubitably\""));
    }

    /**
     * Test toString.
     */
    @Test
    public void ArgsToStringTest() {
        Args args = new Args("bant");
        assertTrue(args.toString().equals("bant"));
    }

}
