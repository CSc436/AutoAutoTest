package test;

import static org.junit.Assert.*;
import model.TestCase;

import org.junit.Test;

import view.ViewController;

/**
 * Tests for the view controller
 * @author jeffersd
 *
 */

public class ViewControllerTest {
	
	/**
	 * Tests the creation of the ViewController Class
	 */
	@Test
	public void testCreateController() {
		ViewController VC = new ViewController();
		assertTrue(VC != null);
	}
	
	
	/**
	 * Tests the creation and storage of tests.
	 * It creates ten thousand tests.
	 */
	@Test
	public void testGenerateTest() {
		ViewController VC = new ViewController();
		for (int i = 0; i < 10000; i++) {
			VC.setAllFields(Integer.toString(i));
			TestCase T = VC.generateTest();
			assertEquals(VC.TC.getTest(i), T);
		}
		assertEquals(10000, VC.getNumberOfTests());
	}
	
	
}
