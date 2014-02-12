package model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArgsTest {

    /**
     * very basic tests of the Args wrapper class.
     */
    @Test
    public void ArgsTest() {

        Args args = new Args();
        assertTrue(args.getArgsValue().equals(""));

        args.setArgsValue("x, false, 3.14");
        assertTrue(args.getArgsValue().equals("x, false, 3.14"));

        args = new Args("\"indubitably\"");
        assertTrue(args.getArgsValue().equals("\"indubitably\""));

        assertTrue(args.toString().equals(args.getArgsValue()));
    }
}
