package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.RelaxedStringFloatCheck;

import org.junit.Test;

/**
 * @author wcohen
 * 
 */
public class RelaxedStringFloatTest {
    /**
     * These unit tests test the relaxed checking with ONLY differences in
     * whitespace being ignored.
     */
    @Test
    public void testWhitespaceRelaxOnly() {
        RelaxedStringFloatCheck myRSFC = new RelaxedStringFloatCheck(false,
                true, false, 5);
        String strExp1 = "There are 3 products in your shopping cart.";
        String strExp2 = "When life hands you lemons  ";
        String strAct1 = "  There    are 3 productsin your shopping cart. ";
        String strAct2 = "Thereare3productsi ny ourshopping cart.";
        String strAct3 = "There  are3products  in your shopp ing cart.  ";
        String strAct4 = "There.are 3 products in your shopping cart.";
        String strAct5 = "There are 0 products in your shopping cart.";
        String strAct6 = "there are 3 products in your Shopping Cart.";
        String strAct7 = "When life hands you lemons ";
        String strAct8 = "When  life  hands  you  lemons";
        boolean result = myRSFC.isAcceptable(strExp1, strAct1);
        assertTrue(result);
        result = myRSFC.isAcceptable(strExp1, strAct2);
        assertTrue(result);
        result = myRSFC.isAcceptable(strExp1, strAct3);
        assertTrue(result);
        result = myRSFC.isAcceptable(strExp1, strAct4);
        assertFalse(result);
        result = myRSFC.isAcceptable(strExp1, strAct5);
        assertFalse(result);
        result = myRSFC.isAcceptable(strExp1, strAct6);
        assertFalse(result);

        result = myRSFC.isAcceptable(strExp2, strAct7);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp2, strAct8);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp2, strExp2);
        assertTrue(result);

    }

    /**
     * These unit tests test the relaxed checking with ONLY differences in
     * uppercase/lowercase being ignored.
     */
    @Test
    public void testCasingRelaxOnly() {
        RelaxedStringFloatCheck myRSFC = new RelaxedStringFloatCheck(true,
                false, false, 5);
        String strExp = "There are 3 products in your shopping cart.";
        String strAct1 = "THERE ARE 3 PRODUCTS IN YOUR SHOPPING CART.";
        String strAct2 = "there are 3 products in your shopping cart.";
        String strAct3 = "There  are 3 pro ducts in your shopping cart.";
        String strAct4 = " there are 3 products in your shopping cart.";

        boolean result = myRSFC.isAcceptable(strExp, strAct1);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp, strAct2);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp, strAct3);
        assertFalse(result);

        result = myRSFC.isAcceptable(strExp, strAct4);
        assertFalse(result);

    }

    /**
     * These unit tests test the relaxed checking with ONLY differences in
     * punctuation being ignored.
     */
    @Test
    public void testPunctuationRelaxOnly() {
        RelaxedStringFloatCheck myRSFC = new RelaxedStringFloatCheck(false,
                false, true, 5);
        String strExp1 = "There are 3 products in your shopping cart.";
        String strExp2 = "When life hands you lemons...";
        String strAct1 = ".There are 3 products in your shopping cart";
        String strAct2 = "There are 3 products in your shopping cart!";
        String strAct3 = "There are 3 products. in your shopping cart..";
        String strAct4 = "There are 3 products. in your shopping cart";
        String strAct5 = "There are.3.products in your shopping cart.";
        String strAct6 = "~There are 3 products in your shopping cart|.";
        String strAct7 = "...When life hands you lemons";
        String strAct8 = "When life hands you lemons!";

        boolean result = myRSFC.isAcceptable(strExp1, strAct1);
        assertTrue(result);

        result = myRSFC.isAcceptable(strAct1, strExp1);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp1, strAct2);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp1, strAct3);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp1, strAct4);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp1, strAct5);
        assertFalse(result);

        result = myRSFC.isAcceptable(strExp1, strAct6);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp2, strAct7);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp2, strAct8);
        assertTrue(result);
    }

    /**
     * These unit tests test the relaxed checking with ONLY decimal rounding
     * (beyond 3 decimal places) being ignored.
     */
    @Test
    public void testRoundingOnly() {
        RelaxedStringFloatCheck myRSFC = new RelaxedStringFloatCheck(false,
                false, false, 3);
        String strExp = "There are 3 products which cost less than $35.00";
        String strAct1 = "There are 3 products which cost less than $34.9999";
        String strAct2 = "There are 3 products which cost less"
                +
                " than $35.00000000001";
        String strAct3 = "There are 3.0 products which cost less than $35";
        String strAct4 = "There are 30 products which cost less than $35.00";

        boolean result = myRSFC.isAcceptable(strExp, strAct1);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp, strAct2);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp, strAct3);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp, strAct4);
        assertFalse(result);

    }

    /**
     * These unit tests test what happens if an invalid precision argument is
     * provided.
     */
    @Test
    public void testInvalidPrecisionInput() {
        RelaxedStringFloatCheck myRSFC = new RelaxedStringFloatCheck(false,
                false, false, -1);
        String strExp = "Pi to 2 decimal places is 3.14";
        String strAct = "Pi to 2 decimal places is 3.14159";

        boolean result = myRSFC.isAcceptable(strExp, strAct);
        assertTrue(result);
    }

    /**
     * These unit tests test having expected and actual of different length.
     */
    @Test
    public void testDifferentLengthInputs() {
        RelaxedStringFloatCheck myRSFC = new RelaxedStringFloatCheck(false,
                false, false, 3);
        String strExp1 = "Qwertyuiop";
        String strExp2 = "Cost is 4.00";
        String strExp3 = "Cost is     ";
        String strAct1 = "Qwerty";
        String strAct2 = "Qwertyuiopasdf";
        String strAct3 = "Cost is  ";

        boolean result = myRSFC.isAcceptable(strExp1, strAct1);
        assertFalse(result);

        result = myRSFC.isAcceptable(strExp1, strAct2);
        assertFalse(result);

        result = myRSFC.isAcceptable(strExp2, strAct3);
        assertFalse(result);

        result = myRSFC.isAcceptable(strAct3, strExp2);
        assertFalse(result);

        result = myRSFC.isAcceptable(strExp3, strAct3);
        assertFalse(result);

        result = myRSFC.isAcceptable(strAct3, strExp3);
        assertFalse(result);
    }

    /**
     * These unit tests test the relaxed checking with ONLY differences in
     * whitespace and punctuation being ignored.
     */
    @Test
    public void testWhitespaceAndPunct() {
        RelaxedStringFloatCheck myRSFC = new RelaxedStringFloatCheck(false,
                true, true, 2);
        String strExp1 = "There are 3 items in your cart.";
        String strAct1 = ".There are 3items in your cart ";
        String strAct2 = "...There are 3 items in your cart.";
        String strAct3 = "There  are  3  items  in  your  cart  .";
        String strAct4 = "There are 300 items in your cart.";

        boolean result = myRSFC.isAcceptable(strExp1, strAct1);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp1, strAct2);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp1, strAct3);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp1, strAct4);
        assertFalse(result);
    }

    /**
     * These unit tests test the relaxed checking with ONLY differences in
     * whitespace and uppercase/lowercase being ignored.
     */
    @Test
    public void testWhitespaceAndCasing() {
        RelaxedStringFloatCheck myRSFC = new RelaxedStringFloatCheck(true,
                true, false, 2);
        String strExp1 = "There are 33 items in your cart.";
        String strAct1 = "thereare33itemsinyourcart.";
        String strAct2 = "THERE ARE  33  ITEMS IN YOUR CART.";
        String strAct3 = "There.are.33.items.in.your.cart.";

        boolean result = myRSFC.isAcceptable(strExp1, strAct1);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp1, strAct2);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp1, strAct3);
        assertFalse(result);
    }

    /**
     * These unit tests test the relaxed checking with ONLY differences in
     * punctuation and uppercase/lowercase being ignored.
     */
    @Test
    public void testPunctAndCasing() {
        RelaxedStringFloatCheck myRSFC = new RelaxedStringFloatCheck(true,
                false, true, 3);
        String strExp1 = "There are 10 items in your cart.";
        String strAct1 = "THERE ARE 10 ITEMS IN YOUR CART,";
        String strAct2 = "there are 10 items. in your cart";
        String strAct3 = "THEREARE10ITEMSINYOURCART.";

        boolean result = myRSFC.isAcceptable(strExp1, strAct1);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp1, strAct2);
        assertTrue(result);

        result = myRSFC.isAcceptable(strExp1, strAct3);
        assertFalse(result);
    }

    /**
     * Additional unit tests to try and cover hard-to-reach branches in the
     * program.
     */
    @Test
    public void testCodeCoverage() {
        RelaxedStringFloatCheck myRSFC = new RelaxedStringFloatCheck(true,
                true, true, 2);
        String strExp1 = "T....b";
        String strAct1 = "T..";

        boolean result = myRSFC.isAcceptable(strExp1, strAct1);
        assertFalse(result);

        result = myRSFC.isAcceptable(strAct1, strExp1);
        assertFalse(result);
    }
}